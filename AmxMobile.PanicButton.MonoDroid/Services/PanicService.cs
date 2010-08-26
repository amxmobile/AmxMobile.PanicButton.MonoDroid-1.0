using System;
using System.IO;
using System.Net;
using System.Xml;
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
    public class PanicService : ServiceBase
    {
        public PanicService()
            : base("panicrest.aspx")
        {
        }

        public void StartPanicking()
        {
            RestRequestArgs args = new RestRequestArgs("startpanicking");
            args["ResourceId"] = PanicRuntime.ResourceId;

            // send...
            this.SendRequest(args);
        }
    }
}