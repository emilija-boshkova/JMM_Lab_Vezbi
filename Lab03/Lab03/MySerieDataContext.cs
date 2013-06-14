using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab03
{
    public class MySerieDataContext:DataContext
    {
        public static string DBConnectionString = "Data Source=isostore:/MySeries.sdf";
        public MySerieDataContext(string connectionString): base(connectionString){ }

        public Table<MySerie> MySeries;


    }
}
