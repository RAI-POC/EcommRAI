var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var moment = require('moment');

var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";
var port = 3001;

app.get('/', function(req, res){
    res.sendfile('indexdyna.html');
});
    var seriesData = [];
 var dataPoint=[];
 var PlacedCnt;
 var AcknowledgedCnt;
 var PickedCnt;
 var ShippedOrDeliveredCnt;
 var CancelledCnt;
 
 var TopPrdtNm1;
 var TopPrdtNm2;
 var TopPrdtNm3;
 var TopPrdtCnt1;
 var TopPrdtCnt2;
 var TopPrdtCnt3;
 var PickUpOrdrCnt;
 var PickUpOrdrTtl;
 var DelivryOrdrCnt;
 var DelivryOrdrTtl;
 
 var OrderCrossed1Hr =0;
 var OrderCrossed2Hr=0;
 var OrderCrossed3Hr=0;
 var OrderCrossed4Hr=0;
 var OrderCrossed5Hr=0;
 var storeId;
 var TotalOrderCnt=0;
io = io.on('connection', function(socket){
    console.log('a user connected');
 
MongoClient.connect(url, function(err, db) {
  if (err) throw err;
  console.log("Database created!");
  var dbo = db.db("mydb");

  //OrderCount in different Status - Placed, Acknowledged, Picked, Shipped/Delivered, Cancelled
dbo.collection("Status").find({"status": "Placed"}).toArray(function(err, result) {
    if (err) throw err;
	PlacedCnt=0;
	for (i = 0; i <result.length; i=i+1){ 
		var strtDate =  result[i].currentStatusTs;
		var endDate = new Date();
		var a = moment(endDate);
		var b = moment(strtDate);
		var noOfDays= (a.diff(b, 'days'));
		if(noOfDays=1){
			PlacedCnt=PlacedCnt+1;
		}
	}
	console.log("Count of Order PlacedCnt:"+PlacedCnt);
  });
dbo.collection("Status").find({"status": "Acknowledged"}).toArray(function(err, result) {
    if (err) throw err;
	AcknowledgedCnt=0;
	OrderCrossed1Hr=0
	OrderCrossed2Hr=0
	OrderCrossed3Hr=0
	OrderCrossed4Hr=0
	OrderCrossed5Hr=0
	for (i = 0; i <result.length; i=i+1){ 
		var strtDate =  result[i].currentStatusTs;
		var endDate = new Date();
		var a = moment(endDate);
		var b = moment(strtDate);
		var noOfDays= (a.diff(b, 'days'));
		if(noOfDays=1){
			AcknowledgedCnt=AcknowledgedCnt+1;
			//console.log("Count of Order Acknowledged:"+AcknowledgedCnt);
			//j=j+1;
		}
		var noOfHours= (a.diff(b, 'hours'));
		//console.log("No of Hours Diffrence is:"+noOfDays);
		if(noOfHours >=1 && noOfHours<2){
			OrderCrossed1Hr=OrderCrossed1Hr+1
		}
		if(noOfHours >=2 && noOfHours<3){
			OrderCrossed2Hr=OrderCrossed2Hr+1
		}
		if(noOfHours >=3 && noOfHours<4){
			OrderCrossed3Hr=OrderCrossed3Hr+1
		}
		if(noOfHours >=4 && noOfHours<5){
			OrderCrossed4Hr=OrderCrossed4Hr+1
		}
		if(noOfHours >5 ){
			OrderCrossed5Hr=OrderCrossed5Hr+1
		}
	}
	console.log("Count of Order Acknowledged:"+AcknowledgedCnt);
});
dbo.collection("Status").find({"status": "Picked"}).toArray(function(err, result) {
    if (err) throw err;
	PickedCnt=0;
	for (i = 0; i <result.length; i=i+1){ 
		var strtDate =  result[i].currentStatusTs;
		var endDate = new Date();
		var a = moment(endDate);
		var b = moment(strtDate);
		var noOfDays= (a.diff(b, 'days'));
		if(noOfDays=1){
			PickedCnt=PickedCnt+1;
			
			//j=j+1;
		}
	}
	console.log("Count of Order Picked:"+PickedCnt);
  });
dbo.collection("Status").find({"status": "Shipped/Delivered"}).toArray(function(err, result) {
    if (err) throw err;
	ShippedOrDeliveredCnt=0;
	for (i = 0; i <result.length; i=i+1){ 
		var strtDate =  result[i].currentStatusTs;
		var endDate = new Date();
		var a = moment(endDate);
		var b = moment(strtDate);
		var noOfDays= (a.diff(b, 'days'));
		if(noOfDays=1){
			ShippedOrDeliveredCnt=ShippedOrDeliveredCnt+1;
			
			//j=j+1;
		}
	}
	console.log("Count of Order Shipped/Delivered:"+ShippedOrDeliveredCnt);
  });
dbo.collection("Status").find({"status": "Cancelled"}).toArray(function(err, result) {
    if (err) throw err;
	CancelledCnt=0;
	for (i = 0; i <result.length; i=i+1){ 
		var strtDate =  result[i].currentStatusTs;
		var endDate = new Date();
		var a = moment(endDate);
		var b = moment(strtDate);
		var noOfDays= (a.diff(b, 'days'));
		if(noOfDays=1){
			CancelledCnt=CancelledCnt+1;
			
		//	j=j+1;
		}
		
	}
console.log("Count of Order Cancelled:"+CancelledCnt);
  });
  
 // Query to get top 3 products from mongo db
 var agrMax=[
     
      { $group: { _id:"$itemdesc", count: { $sum: 1 } } },
	{ $sort: { count: -1 } },
	{ $limit: 3 }
   ];

 var1=dbo.collection("Items").aggregate(agrMax).toArray( (err, res) => {
	if( res !=''){   
	TopPrdtNm1 = res[0]._id;
	TopPrdtNm2 = res[1]._id;
	TopPrdtNm3 = res[2]._id;
	TopPrdtCnt1= res[0].count;
	TopPrdtCnt2= res[1].count;
	TopPrdtCnt3= res[2].count;
console.log("Max itemdesc1 "+ res[0]._id +"and count  "+res[0].count);	
console.log("Max itemdesc2 "+ res[1]._id +"and count  "+res[1].count);	
console.log("Max itemdesc3 "+ res[2]._id +"and count  "+res[2].count);	
     }
    }); 
	
//To get OrderCount and  OrderTotal for pickup and delivery.
var agrDelivery=[
     {
	 $match: {"orderType":"Delivery"}},
      { $group:
         {
           _id: 1,totalAmount: { $sum: "$orderTotal" },count:{$sum:1}
         }
     }
   ];
 

var3=dbo.collection("Order").aggregate(agrDelivery).toArray( (err, res) => {  
	if( res !=''){   
		DelivryOrdrCnt=res[0].count;
		DelivryOrdrTtl=res[0].totalAmount
		console.log("totalAmount for Delivery is"+ res[0].totalAmount +"and total count of orders "+res[0].count);
     }
    }); 
	
var agrPickUp=[
     {
	 $match: {"orderType":"Pickup"}},
      { $group:
         {
           _id: 1,totalAmount: { $sum: "$orderTotal" },count:{$sum:1}
         }
     }
   ];

var4=dbo.collection("Order").aggregate(agrPickUp).toArray( (err, res1) => {
      if( res1 !=''){   
		PickUpOrdrCnt=res1[0].count;
		PickUpOrdrTtl=res1[0].totalAmount
		console.log("totalAmount for PickUp is"+ res1[0].totalAmount +"and total count of orders "+res1[0].count);
     }
    });  
	

 dbo.collection("Order").find({}).toArray(function(err, result) {
    if (err) throw err;
	storeId= result[0].store;
   // console.log("result are:"+JSON.stringify(result));
  //console.log("result for Order are:"+result.length);
  db.close();
  });
 });


dataPoint={
    "placed":PlacedCnt,
     "acknowledged":AcknowledgedCnt,
     "picked":PickedCnt,
	 "shippedOrdelivered":ShippedOrDeliveredCnt,
	 "cancelled":CancelledCnt,
	 
	 "productName1":TopPrdtNm1,
	 "productName2":TopPrdtNm2,
	 "productName3":TopPrdtNm3,
	 "productCnt1":TopPrdtCnt1,
	 "productCnt2":TopPrdtCnt2,
	 "productCnt3":TopPrdtCnt3,
	 
	 "PickUpCnt":PickUpOrdrCnt,
	 "PickUpTtl":PickUpOrdrTtl,
	 "deliveryCnt":DelivryOrdrCnt,
	 "DeliveryTtl":DelivryOrdrTtl,
	  "StoreNum":storeId,
	 
	 "OrdrCntCrss1Hr":OrderCrossed1Hr,
	 "OrdrCntCrss2Hr":OrderCrossed2Hr,
	 "OrdrCntCrss3Hr":OrderCrossed3Hr,
	 "OrdrCntCrss4Hr":OrderCrossed4Hr,
	 "OrdrCntCrss5Hr":OrderCrossed5Hr,
 }
 console.log("dataPoint"+ JSON.stringify(dataPoint));
 socket.emit("message", dataPoint); 
 
    socket.on('disconnect', function(){
        console.log('user disconnected');
    });
});
http.listen(port, function(){
    console.log("Running on port " + port)
});
