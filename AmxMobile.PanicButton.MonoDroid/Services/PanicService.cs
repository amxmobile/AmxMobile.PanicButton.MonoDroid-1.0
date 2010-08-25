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

namespace AmxMobile.PanicButton.MonoDroid.Services
{
    public abstract class PanicService
    {
        private string ServiceName { get; set; }

        protected PanicService(string serviceName)
        {
            this.ServiceName = serviceName;
        }

        private string ServiceUrl
        {
            get
            {
                return "http://192.168.1.105/amxpanic/webservices/" + this.ServiceName;
            }
        }

        public XmlElement SendRequest(RestRequestArgs args)
        {
            // get...
            XmlDocument doc = args.ToXmlDocument();

            // send...
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(this.ServiceUrl);

            // bytes...
            byte[] bs = Encoding.UTF8.GetBytes(doc.OuterXml);
            request.ContentLength = bs.Length;
            request.ContentType = "text/xml";
            using (Stream stream = request.GetRequestStream())
                stream.Write(bs, 0, bs.Length);

            // run...
            HttpWebResponse response = (HttpWebResponse)request.GetResponse();
            
            // load an answer...
            XmlDocument answer = new XmlDocument();
            using (Stream stream = response.GetResponseStream())
                answer.Load(stream);

            // response...
            XmlElement responseElement = (XmlElement)answer.SelectSingleNode("RestResponse");
            if(responseElement == null)
	            throw new InvalidOperationException("'responseElement' is null.");

            // find...
            XmlElement hasErrorElement = (XmlElement)responseElement.SelectSingleNode("HasError");
            if (hasErrorElement == null)
                throw new InvalidOperationException("'hasErrorElement' is null.");

            // is there a peoblem?
            if (hasErrorElement.Value == "1")
            {
                XmlElement errorElement = (XmlElement)responseElement.SelectSingleNode("Error");
                if (errorElement != null)
                    throw new InvalidOperationException(errorElement.Value);
                else
                    throw new InvalidOperationException("An error occurred on the server, but no additional information was provided.");
            }

            // return...
            return responseElement;
        }
    }
}