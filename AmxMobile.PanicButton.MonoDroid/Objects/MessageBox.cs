using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;

namespace AmxMobile.PanicButton.MonoDroid
{
    public sealed class MessageBox
    {
        public static void Show(Context context, string message)
        {
		    AlertDialog.Builder builder = new AlertDialog.Builder(context);
		    builder.SetTitle("Simplehosted");
		    builder.SetMessage(message);
            builder.SetPositiveButton("OK", new Listener());
		
		    // show...
		    AlertDialog dialog = builder.Create();
		    dialog.Show();
        }

        private class Listener : IDialogInterfaceOnClickListener
        {
            public void OnClick(IDialogInterface dialog, int which)
            {
                throw new NotImplementedException();
            }

            public IntPtr Handle
            {
                get { throw new NotImplementedException(); }
            }
        }
    }
}