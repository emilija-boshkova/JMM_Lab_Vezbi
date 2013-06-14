using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab03
{
    public class ViewModelSeries:INotifyPropertyChanged
    {
        //private MySerieDataContext SeriesDB;
        public ObservableCollection<MySerie> MySeries { get; set; }
        public void GetSeries()
        {
            ObservableCollection<MySerie> a = new ObservableCollection<MySerie>();
            using(MySerieDataContext SeriesDB=new MySerieDataContext(MySerieDataContext.DBConnectionString))
            {
                var query = from c in SeriesDB.MySeries select c;
                MySeries = new ObservableCollection<MySerie>(query);
            }

        }
        public void Delete(MySerie s)
        {
            MySeries.Remove(s);
            using (MySerieDataContext SeriesDB = new MySerieDataContext(MySerieDataContext.DBConnectionString))
            {
                IQueryable<MySerie> seriequery = from c in SeriesDB.MySeries where c.title == s.title select c;
                MySerie ds = seriequery.FirstOrDefault();
                SeriesDB.MySeries.DeleteOnSubmit(ds);
                SeriesDB.SubmitChanges();
            }
        }

        #region INotifyPropertyChanged Members

    public event PropertyChangedEventHandler PropertyChanged;

    // Used to notify the app that a property has changed.
    private void NotifyPropertyChanged(string propertyName)
    {
        if (PropertyChanged != null)
        {
            PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
        }
    }
    #endregion


    }
}