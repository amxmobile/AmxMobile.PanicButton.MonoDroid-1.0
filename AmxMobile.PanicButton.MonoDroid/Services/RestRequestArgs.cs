using System;
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
    public class RestRequestArgs : Dictionary<string, string>
    {
        public RestRequestArgs(string operation)
        {
            this["operation"] = operation;
        }

        public XmlDocument ToXmlDocument()
        {
            XmlDocument doc = new XmlDocument();
            XmlElement root = doc.CreateElement("RestArgs");
            doc.AppendChild(root);

            // walk...
            foreach (string name in this.Keys)
            {
                XmlElement element = doc.CreateElement(name);
                root.AppendChild(element);
                element.Value = this[name];
            }

            // return...
            return doc;
        }
    }
}