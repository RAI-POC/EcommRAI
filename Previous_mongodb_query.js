var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";
var moment = require('moment');
//var url = "mongodb+srv://mongouser_01:mongouser01@ecomstream-tv6mm.mongodb.net/test?retryWrites=true";
MongoClient.connect(url, function(err, db) {
  if (err) throw err;
  console.log("Database created!");
  var dbo = db.db("mydb");
 //query to insert many records

var date= moment.duration().asHours();


 var myorder = [{ "orderNumber":1, "orderType": "Pickup", "store":1234, "orderTotal":10 },
{ "orderNumber":1, "orderType": "Pickup", "store":1234, "orderTotal":10 },
{ "orderNumber":1, "orderType": "Pickup", "store":1234, "orderTotal":10 },
{ "orderNumber":1, "orderType": "Pickup", "store":1234, "orderTotal":10 },
{ "orderNumber":1, "orderType": "Pickup", "store":1234, "orderTotal":10 },
{ "orderNumber":1, "orderType": "Delivery", "store":1234, "orderTotal":10 },
{ "orderNumber":1, "orderType": "Delivery", "store":1234, "orderTotal":10 },
{ "orderNumber":1, "orderType": "Delivery", "store":1234, "orderTotal":20},
{ "orderNumber":1, "orderType": "Delivery", "store":1234, "orderTotal":20 },
{ "orderNumber":1, "orderType": "Delivery", "store":1234, "orderTotal":20 }]


dbo.collection("Order").insertMany(myorder, function(err, res) {
    if (err) throw err;
    console.log("Number of documents inserted: " + res.insertedCount);
    db.close();
  });

   
   
var mystaus=[
{"orderNumber":1, "status": "Placed", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Placed", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Placed", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Acknowledged", "store": 1234, "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Acknowledged", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Acknowledged", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Acknowledged", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Picked", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Picked", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Picked", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Picked", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Picked", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Picked", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Picked", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Shipped/Delivered", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Shipped/Delivered", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Shipped/Delivered", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Shipped/Delivered", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Shipped/Delivered", "store": 1234,  "currentStatusTs": new Date()},

{"orderNumber":1, "status": "Cancelled", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Cancelled", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Cancelled", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Cancelled", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Cancelled", "store": 1234,  "currentStatusTs": new Date()},
{"orderNumber":1, "status": "Cancelled", "store": 1234,  "currentStatusTs": new Date()}]
 
dbo.collection("Status").insertMany(mystaus, function(err, res) {
    if (err) throw err;
    console.log("Number of documents inserted: " + res.insertedCount);
  // db.close();
  }); 
   
  
var myItems=[
  {"orderNumber": 1, "itemdesc": "tv", "qty": 20},
			   {"orderNumber": 1, "itemdesc": "tv", "qty": 1},
			   {"orderNumber": 1, "itemdesc": "tv", "qty": 1},
			   {"orderNumber": 1, "itemdesc": "tv", "qty": 1},
			   {"orderNumber": 1, "itemdesc": "tv", "qty": 1},
			   {"orderNumber": 1, "itemdesc": "AC", "qty": 1},
			   {"orderNumber": 1, "itemdesc": "AC", "qty": 1},
			   {"orderNumber": 1, "itemdesc": "AC", "qty": 1},
			   {"orderNumber": 1, "itemdesc": "AC", "qty": 1},
			   {"orderNumber": 1, "itemdesc": "Fridge", "qty": 1},
			   {"orderNumber": 1, "itemdesc": "Fridge", "qty": 1},
			   {"orderNumber": 1, "itemdesc": "Fridge", "qty": 1},
			   {"orderNumber": 1, "itemdesc": "Fridge", "qty": 1},
			   {"orderNumber": 1, "itemdesc": "washingmachine", "qty": 1}  
  ] 
 
 dbo.collection("Items").insertMany(myItems, function(err, res) {
     if (err) throw err;
     console.log("Number of documents inserted: " + res.insertedCount);
    //db.close();
  }); 
 
 dbo.collection("Items").find({}).toArray(function(err, result) {
    if (err) throw err;
	
   // console.log("result are:"+JSON.stringify(result));
  console.log("result for Items are:"+result.length);
  //  db.close();
  });
  
// var max=  dbo.collection("Items").find().max( { item: 'mango', type: 'jonagold' } ).hint( { item: 1, type: 1 } )
 // var min= dbo.collection("Items").find().max( { item: 'mango', type: 'jonagold' } ).hint( { item: 1, type: 1 } )
  
 var agrMax=[
     
      { $group: { _id:"$itemdesc", count: { $sum: 1 } } },
	{ $sort: { count: -1 } },
	{ $limit: 4 }
   ];

 var1=dbo.collection("Items").aggregate(agrMax).toArray( (err, res) => {
   
	if( res !=''){   
	//	console.log("MaxCount is"+ res[0].MaxCount);
console.log("Max itemdesc1 is"+ res[0]._id);	
console.log("Max count1 is"+ res[0].count);	  
console.log("Max itemdesc2 is"+ res[1]._id);
console.log("Max count2 is"+ res[1].count);	 	 
console.log("Max itemdesc3 is"+ res[2]._id);	
console.log("Max count3 is"+ res[2].count);	
console.log("Max itemdesc4 is"+ res[3]._id);	
console.log("Max count4 is"+ res[3].count);   
     }
    }); 
	

 
 
 
//query to delete records:
//var myquery = { status: "Acknowledged" };
 //dbo.collection("Status").deleteMany(myquery, function(err, obj) {
 //   if (err) throw err;
//   console.log("1 document deleted");
 //   db.close();
 // });

//var myquery = { orderType: "Pickup" };
 //dbo.collection("Order").deleteMany(myquery, function(err, obj) {
 //  if (err) throw err;
//  console.log("1 document deleted");
 //   db.close();
 //});

// var myquery1 = { orderType: "Delivery" };
 //dbo.collection("Order").deleteMany(myquery1, function(err, obj) {
 //  if (err) throw err;
 // console.log("1 document deleted");
 //   db.close();
// });
 
dbo.collection("Status").find({"status": "Placed"}).toArray(function(err, result) {
    if (err) throw err;
//    console.log("result are:"+JSON.stringify(result));
  console.log("result for Status are:"+result.length);
 //   db.close();
 var OrderCrossed1Hr =0;
 var OrderCrossed2Hr=0;
 var OrderCrossed3Hr=0;
 var OrderCrossed4Hr=0;
 var OrderCrossed5Hr=0;
	for (i = 0; i <result.length; i=i+1){ 
	//if(result[i].status ="Acknowledged"){
//	console.log("value for i:"+i);
		var strtDate =  result[i].currentStatusTs;
		//console.log("strtDate:"+strtDate);
		var endDate = new Date();
		//console.log("endDate:"+endDate);
					var a = moment(endDate);
					var b = moment(strtDate);
					//console.log("a and B values:"+a+b);
					var noOfHours= (a.diff(b, 'hours'));
		//console.log("No of Hours Diffrence is:"+noOfDays);
		if(noOfHours >=1 && noOfHours<2){
			OrderCrossed1Hr=OrderCrossed1Hr+1
		}
		if(noOfHours >=2 && noOfHours<3){
			OrderCrossed2Hr=OrderCrossed1Hr+1
		}
		if(noOfHours >=3 && noOfHours<4){
			OrderCrossed3Hr=OrderCrossed1Hr+1
		}
		if(noOfHours >=4 && noOfHours<5){
			OrderCrossed4Hr=OrderCrossed1Hr+1
		}
		if(noOfHours >5 ){
			OrderCrossed5Hr=OrderCrossed1Hr+1
		}
		
	// }
	}
		console.log("OrderCrossed1Hr:"+OrderCrossed1Hr);
  });
// to get COunt of totalOder for Pickup/Delivary  
 var PickupTotalOrder;
 var DeliveryTotalOrder;
 
 dbo.collection("Order").find({"orderType": "Pickup"} ).toArray(function(err, result) {
    if (err) throw err;
	PickupTotalOrder = result.length;
	console.log("TotalOrder count for PickUp:"+PickupTotalOrder);
  });
  dbo.collection("Order").find({"orderType": "Delivery"} ).toArray(function(err, result) {
    if (err) throw err;
	DeliveryTotalOrder = result.length;
	console.log("TotalOrder count for Delivery:"+DeliveryTotalOrder);
  });
  
  //To get sum and total of pickup and delivery.
var totalDeliveryOrder=[]; 
var totalPickupOrder=[]; 

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
		console.log("totalAmount for Delivery is"+ res[0].totalAmount);
	  console.log("count for Delivery is"+ res[0].count);
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
      console.log("totalAmount for Pickup is"+ res1[0].totalAmount);
	  console.log("count for Pickup is"+ res1[0].count);
     }
    });   

// dbo.collection("Order").find({}).toArray(function(err, result) {
    // if (err) throw err;
	
   // // console.log("result are:"+JSON.stringify(result));
  // //console.log("result for Order are:"+result.length);
  // //  db.close();
  // });
 
 
 
});
