package mono;

import java.io.*;
import java.lang.String;
import java.util.HashSet;
import java.util.zip.*;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.AssetManager;
import android.util.Log;
import mono.android.Runtime;

public class MonoPackageManager {
	static String sharedRuntimePath;

	static boolean initialized;
	static HashSet<String> registeredTypes = new HashSet<String> ();

	static final String TAG = "MonoPackageManager";

	static void CopyFile (ContextWrapper context, InputStream instream, String path) throws IOException
	{
		OutputStream outstream = null;
		try {
			outstream = context.openFileOutput (path, 0);
			byte[] buf = new byte[1024];
			int len;
			while ((len = instream.read (buf)) > 0) {
				outstream.write (buf, 0, len);
			}
		} finally {
			Close (instream);
			Close (outstream);
		}
	}

	static void Close (Closeable c)
	{
		try {
			c.close ();
		}
		catch (Throwable t) {
			Log.e (TAG, "Unable to close resource", t);
		}
	}

	static void UnpackAssemblies (ContextWrapper context)
	{
		AssetManager mgr = context.getAssets ();
		String pkg = context.getPackageName ();
		try {
			ZipFile apk = new ZipFile (context.getPackageCodePath ());
			CopyFiles (context, apk, MonoPackageManager_Resources.Assemblies);
			CopyFiles (context, apk, MonoPackageManager_Resources.Dependencies);
		} catch (Throwable ex) {
			Log.e (TAG, "Unable to Unpack files.", ex);
			throw new UnsupportedOperationException (ex);
		}
	}

	static void CopyFiles (ContextWrapper context, ZipFile apk, String[] files) throws IOException
	{
		for (int i = 0; i < files.length; ++i) {
			String assembly = files [i];
			ZipEntry zipentry = apk.getEntry (assembly);
			File file = context.getFileStreamPath (assembly);
			if (file.exists () && file.lastModified () == zipentry.getTime ())
				continue;
			CopyFile (context, apk.getInputStream (zipentry), assembly);
			file.setLastModified (zipentry.getTime ());

			String mdb = assembly + ".mdb";
			zipentry = apk.getEntry (mdb);
			if (zipentry == null)
				continue;
			CopyFile (context, apk.getInputStream (zipentry), mdb);
		}
	}

	public static void setSharedRuntimePath (String val)
	{
		sharedRuntimePath = val;
	}

	static String getSharedRuntimePath ()
	{
		return sharedRuntimePath;
	}

	public static void LoadPackage (ContextWrapper context, Intent intent, String registrationType, Class nativeType, String methods, Boolean shared)
	{
		synchronized (registeredTypes) {
			if (!initialized) {
				System.loadLibrary("monodroid");

				UnpackAssemblies (context);
				Runtime.init (shared == Boolean.TRUE ? getSharedRuntimePath () : context.getFilesDir ().getAbsolutePath (), context.getFilesDir ().getAbsolutePath (), intent.getStringExtra ("mono:runtime-args"), MonoPackageManager_Resources.Assemblies);
				initialized = true;
			}
			if (!registeredTypes.contains (registrationType)) {
				Runtime.register (registrationType, nativeType, methods);
				registeredTypes.add (registrationType);
			}
		}
	}
}

class MonoPackageManager_Resources {
	public static final String[] Assemblies = new String[]{
		"AmxMobile.PanicButton.MonoDroid.dll",
	};
	public static final String[] Dependencies = new String[]{
		"Mono.Android.dll",
	};
	public static final String[] Types = new String[]{
		"AmxMobile.PanicButton.MonoDroid.Activity1, AmxMobile.PanicButton.MonoDroid, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null",
	};
}
