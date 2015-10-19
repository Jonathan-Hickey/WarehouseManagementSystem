//Shane Craven - Orion Platform (AUTOSTAMP)

using System;
using Newtonsoft.Json;
using System.IO;
using System.Collections;
using System.Net.Sockets;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Net;

namespace Compcon.zeus_software
{
    public partial class what_server : System.Web.UI.Page
    {
        Dictionary<String, Dictionary<String, Object>> serverMap = new Dictionary<String, Dictionary<String, Object>>();
        protected void Page_Load(object sender, EventArgs e)
        {
            buildServerMap();
            int bestPriority = 0;
            //Set local host to be our default case
            Dictionary<String, Object> bestConnection = serverMap["Local"];
            List<String> keyList = new List<String>(serverMap.Keys);
            foreach (String server in keyList)
            {
                Boolean connected = false;
                if(!server.Equals("Local"))
                {
                    //The address and port of current server
                    IPEndPoint serverAddress = new IPEndPoint(IPAddress.Parse((string)serverMap[server]["endpoint"]), (int)serverMap[server]["port"]);
                    //Create socket to be used to check connection state
                    Socket clientSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                    try
                    {
                        //Attempt to open a connection to the server
                        clientSocket.Connect(serverAddress);
                        //If the connection worked, the server is online and we are free to use it.
                        connected = true;
                    }
                    catch (Exception)
                    {
                        //Connection Failed at this point. This is handled below
                    }
                    finally
                    {
                        clientSocket.Close();
                        if(connected)
                        {
                            //If we were able to connect to this server, use it only if it has a higher priority
                            //then the current best server
                            if((int) serverMap[server]["priority"] > bestPriority)
                            {
                                bestConnection = serverMap[server];
                                bestPriority = (int)serverMap[server]["priority"];
                            }
                        }
                    }
                }
            }
            //Output the result as JSON which will be consumed by the client software
            Response.Write(JsonConvert.SerializeObject(bestConnection));

        }

        private void buildServerMap()
        {
            //List of all servers running our server and an associated priority.
            //It will try and use servers with higher priority first, falling back
            //to localhost if all fails. In an ideal world this function would 
            //retrive this data from a restfulAPI, Amazon S3 bucket or some other 
            //datasource and this would not be hardcoded.
            Dictionary<String, Object> test = new Dictionary<String, Object>();
            test.Add("endpoint", "104.236.24.208");
            test.Add("port", 9090);
            test.Add("server", "Zeus Primary - Light For Dark - New York");
            test.Add("priority", 1);
            serverMap.Add("Server1", test);
            test = new Dictionary<String, Object>();
            test.Add("endpoint", "127.0.0.1");
            test.Add("port", 9090);
            test.Add("server", "localhost");
            test.Add("priority", 0);
            serverMap.Add("Local", test);
        }


    }
}