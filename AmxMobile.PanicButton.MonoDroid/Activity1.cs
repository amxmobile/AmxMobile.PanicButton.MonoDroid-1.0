using System;

using Android.App;
using Android.Content;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;

namespace AmxMobile.PanicButton.MonoDroid
{
    public class Activity1 : Activity
    {
        int count = 1;

        public Activity1(IntPtr handle)
            : base(handle)
        {
        }

        protected override void OnCreate(Bundle bundle)
        {
            base.OnCreate(bundle);

            // Set our view from the "main" layout resource
            SetContentView(R.layout.main);

            // Get our button from the layout resource,
            // and attach an event to it
            Button button = FindViewById<Button>(R.id.myButton);

            button.Click += new View.OnClickEventHandler(button_Click);
        }

        void button_Click(View v)
        {
            try
            {
                // start panicking...
                PanicService service = new PanicService();

                // show...
                MessageBox.Show(this, "You are now panicking!");
            }
            catch (Exception ex)
            {
                MessageBox.Show(this, ex.ToString());
            }
        }
    }
}

