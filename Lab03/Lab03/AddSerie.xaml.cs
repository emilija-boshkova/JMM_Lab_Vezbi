using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;

namespace Lab03
{
    public partial class AddSerie : PhoneApplicationPage
    {
        public AddSerie()
        {
            InitializeComponent();
        }

        private void btnOk_Click(object sender, RoutedEventArgs e)
        {
            using(MySerieDataContext context=new MySerieDataContext(MySerieDataContext.DBConnectionString))
            {
                MySerie serie = new MySerie();
                serie.title = txtTitle.Text;
                context.MySeries.InsertOnSubmit(serie);
                context.SubmitChanges();
            }
            NavigationService.Navigate(new Uri("/PivotPage1.xaml",UriKind.Relative));
        }
    }
}