﻿#pragma checksum "C:\Users\emilija\Desktop\Lab03\Lab03\PivotPage1.xaml" "{406ea660-64cf-4c82-b6f0-42d48172a799}" "D0EDB2B2CCAE2A59D146531C3110051A"
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.18033
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

using Microsoft.Phone.Controls;
using System;
using System.Windows;
using System.Windows.Automation;
using System.Windows.Automation.Peers;
using System.Windows.Automation.Provider;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Interop;
using System.Windows.Markup;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Imaging;
using System.Windows.Resources;
using System.Windows.Shapes;
using System.Windows.Threading;


namespace Lab03 {
    
    
    public partial class PivotPage1 : Microsoft.Phone.Controls.PhoneApplicationPage {
        
        internal System.Windows.Controls.Grid LayoutRoot;
        
        internal System.Windows.Controls.TextBlock txtStart;
        
        internal System.Windows.Controls.ListBox MyList;
        
        internal System.Windows.Controls.ListBox PopularSeries;
        
        private bool _contentLoaded;
        
        /// <summary>
        /// InitializeComponent
        /// </summary>
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        public void InitializeComponent() {
            if (_contentLoaded) {
                return;
            }
            _contentLoaded = true;
            System.Windows.Application.LoadComponent(this, new System.Uri("/Lab03;component/PivotPage1.xaml", System.UriKind.Relative));
            this.LayoutRoot = ((System.Windows.Controls.Grid)(this.FindName("LayoutRoot")));
            this.txtStart = ((System.Windows.Controls.TextBlock)(this.FindName("txtStart")));
            this.MyList = ((System.Windows.Controls.ListBox)(this.FindName("MyList")));
            this.PopularSeries = ((System.Windows.Controls.ListBox)(this.FindName("PopularSeries")));
        }
    }
}
