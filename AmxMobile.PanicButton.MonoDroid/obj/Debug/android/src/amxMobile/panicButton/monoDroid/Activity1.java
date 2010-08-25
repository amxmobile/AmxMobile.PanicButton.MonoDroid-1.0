package amxMobile.panicButton.monoDroid;

import android.os.Bundle;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.novell.monodroid.runtimeservice.IMonoRuntimeService;

public class Activity1
	extends android.app.Activity
{
	private IMonoRuntimeService service = null;
	private ServiceConnection svcConn = new ServiceConnection () {
		@Override
		public void onServiceConnected (ComponentName className, IBinder binder) {
				service = IMonoRuntimeService.Stub.asInterface (binder);
			try {
			_serviceConnected ();
			unbindService (svcConn);
			} catch (android.os.RemoteException re) {
			}
		}
		@Override
		public void onServiceDisconnected (ComponentName className) {
			service = null;
		}
	};
	static final String __md_methods;
	static {
		__md_methods = 
			"n_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\n" +
			"";
	}

	public Activity1 ()
	{
		super ();
	}

	@Override
	public void onCreate (Bundle p0)
	{
		bindService (new Intent ("com.novell.monodroid.runtimeservice.IMonoRuntimeService"), svcConn, Context.BIND_AUTO_CREATE);
		super.onCreate (p0);
	}

	private void _serviceConnected () throws android.os.RemoteException
	{
		mono.MonoPackageManager.setSharedRuntimePath (service.getRuntimePath ());
		mono.MonoPackageManager.LoadPackage (this, getIntent(), "AmxMobile.PanicButton.MonoDroid.Activity1, AmxMobile.PanicButton.MonoDroid, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", Activity1.class, __md_methods, Boolean.TRUE);
		n_onCreate (null);
	}

	private native void n_onCreate (Bundle p0);
}
