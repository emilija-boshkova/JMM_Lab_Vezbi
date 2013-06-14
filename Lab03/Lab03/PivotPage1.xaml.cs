using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using System.Collections.ObjectModel;
using System.Runtime.Serialization.Json;
using System.IO;
using System.Text;
using System.Threading.Tasks;

namespace Lab03
{
    public partial class PivotPage1 : PhoneApplicationPage
    {
        public ObservableCollection<String> MySeries = new ObservableCollection<String>();
        public ViewModelSeries vm;
        public PivotPage1()
        {
            InitializeComponent();
            vm = new ViewModelSeries();
           // if (MyList.Items.Count == 0)
            //    txtStart.Visibility = System.Windows.Visibility.Visible;
            GetPopularCallback();


        }

        private void AddNewSerie(object sender, EventArgs e)
        {
            NavigationService.Navigate(new Uri("/AddSerie.xaml", UriKind.Relative));
        }

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            vm.GetSeries();
            MyList.DataContext = vm.MySeries;
            if (MyList.Items.Count == 0)
                txtStart.Visibility = System.Windows.Visibility.Visible;
            base.OnNavigatedTo(e);
        }

        private void DeleteButton_Click(object sender, RoutedEventArgs e)
        {
            var button = sender as Button;
            if (button != null)
            {
                MySerie s = button.DataContext as MySerie;
                vm.Delete(s);
                MyList.DataContext = vm.MySeries;
                if (MyList.Items.Count == 0)
                    txtStart.Visibility = System.Windows.Visibility.Visible;
            }
        }

        public async void GetPopularCallback()
        {
            var request = HttpWebRequest.Create("http://tvseries.apphb.com/RestServiceImpl.svc/json/getSerie/ga") as HttpWebRequest;
            var factory = new TaskFactory();
            var task = factory.FromAsync<WebResponse>(request.BeginGetResponse, request.EndGetResponse, null);
            try
            {
                var response=await task;
                System.IO.Stream responseStream = response.GetResponseStream();
                string data;
                using (var reader = new System.IO.StreamReader(responseStream))
                {
                    data = reader.ReadToEnd();
                }
                responseStream.Close();
                //MessageBox.Show("Recieved payload of " + data.Length + " characters");
               // MessageBox.Show(data);
               List<Popular> feed = Newtonsoft.Json.JsonConvert.DeserializeObject<List<Popular>>(data);
               PopularSeries.DataContext = feed;
               //MessageBox.Show(feed.Count.ToString());
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
        }

        private void Location_Click(object sender, EventArgs e)
        {
            NavigationService.Navigate(new Uri("/Location.xaml", UriKind.Relative));
        }
    }
}