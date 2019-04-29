<?php
	//http://localhost/xms/webship/generate_TNT_ET_file.php?tsmIDa=3459&disposea=1&delete=1&files=INXP3202040338.dat
	//http://admin.inxpress.com.au/xms/webship/generate_TNT_ET_file.php?disposea=1&delete=1&files=INXP3102041539.dat&tsmIDa=6051
	//For TNT, please change the filename to be INXPAU and schedule to send at 5:30 PM Brisbane time.
	// php /var/www/html/xms_tmp/generate_TNT_ET_file.php >  /var/www/html/xms/edi_file/CronTabLog_generate_TNT_ET_file.txt
	//php /var/www/html/xms_tmp/download_billing_file.php >  /var/www/html/xms/edi_file/CronTabLog_generate_TNT_ET_file.txt
	///xms/edi_file/CronTabLog__generate_TNT_ET_file.txt
$airbill_array="IXX500045836
IXX500045837
IXX500045838
IXX500045839
IXX500045841
IXX500045842
IXX500045843
IXX500045844
IXX500045845
IXX500045846
IXX500045847
IXX500045849
IXX500045850
IXX500045854
IXX500045855
IXX500045856
IXX500045857
IXX500045858
IXX500045859
IXX500045860
IXX500045861
IXX500045862
IXX500045865
IXX500045867
IXX500045869
IXX500045871
IXX500045872
IXX500045874
IXX500045875
IXX500045876
IXX500045878
IXX500045880
IXX500045881
IXX500045884
IXX500045886
IXX500045888
IXX500045889
IXX500045891
IXX500045892
IXX500045893
IXX500045894
IXX500045895
IXX500045897
IXX500045899
IXX500045900
IXX500045901
IXX500045902
IXX500045903
IXX500045905
IXX500045907
IXX500045908
IXX500045909
IXX500045911
IXX500045912
IXX500045913
IXX500045914
IXX500045915
IXX500045919
IXX500045920
IXX500045921
IXX500045922
IXX500045923
IXX500045924
IXX500045925
IXX500045926
IXX500045927
IXX500045928
IXX500045931
IXX500045932
IXX500045934
IXX500045935
IXX500045936
IXX500045937
IXX500045938
IXX500045939
IXX500045940
IXX500045941
IXX500045942
IXX500045943
IXX500045946
IXX500045947
IXX500045950
IXX500045951
IXX500045953
IXX500045956
IXX500045957
IXX500045958
IXX500045959
IXX500045960
IXX500045961
IXX500045962
IXX500045964
IXX500045965
IXX500045967
IXX500045968
IXX500045974
IXX500045975
IXX500045977
IXX500045979
IXX500045981
IXX500045982
IXX500045983
IXX500045985
IXX500045988
IXX500045989
IXX500045990
IXX500045991
IXX500045992
IXX500045993
IXX500045994
IXX500045995
IXX500045996
IXX500045997
IXX500045999
IXX500046000
IXX500046001
IXX500046002
IXX500046004
IXX500046005
IXX500046008
IXX500046009
IXX500046010
IXX500046011
IXX500046013
IXX500046015
IXX500046018
IXX500046020
IXX500046022
IXX500046023
IXX500046024
IXX500046025
IXX500046026
IXX500046027
IXX500046028
IXX500046029
IXX500046030
IXX500046031
IXX500046032
IXX500046034
IXX500046036
IXX500046038
IXX500046039
IXX500046040
IXX500046041
IXX500046042
IXX500046045
IXX500046047
IXX500046048
IXX500046049
IXX500046051
IXX500046052
IXX500046053
IXX500046054
IXX500046055
IXX500046058
IXX500046059
IXX500046060
IXX500046061
IXX500046062
IXX500046063
IXX500046064
IXX500046065
IXX500046067
IXX500046069
IXX500046070
IXX500046072
IXX500046073
IXX500046074
IXX500046075
IXX500046076
IXX500046077
IXX500046079
IXX500046080
IXX500046081
IXX500046083
IXX500046086
IXX500046088
IXX500046092
IXX500046094
IXX500046095
IXX500046096
IXX500046097
IXX500046098
IXX500046100
IXX500046101
IXX500046102
IXX500046104
IXX500046105
IXX500046106
IXX500046107
IXX500046108
IXX500046109
IXX500046111
IXX500046113
IXX500046115
IXX500046116
IXX500046117
IXX500046118
IXX500046121
IXX500046126
IXX500046128
IXX500046129
IXX500046130
IXX500046132
IXX500046134
IXX500046135
IXX500046137
IXX500046138
IXX500046139
IXX500046140
IXX500046141
IXX500046142
IXX500046143
IXX500046145
IXX500046146
IXX500046147
IXX500046148
IXX500046156
IXX500046158
IXX500046159
IXX500046161
IXX500046162
IXX500046163
IXX500046164
IXX500046165
IXX500046166
IXX500046167
IXX500046169
IXX500046170
IXX500046171
IXX500046172
IXX500046174
IXX500046175
IXX500046176
IXX500046177
IXX500046178
IXX500046179
IXX500046180
IXX500046185
IXX500046187
IXX500046189
IXX500046190
IXX500046191
IXX500046193
IXX500046196
IXX500046197
IXX500046198
IXX500046199
IXX500046200
IXX500046202
IXX500046203
IXX500046204
IXX500046205
IXX500046206
IXX500046207
IXX500046208
IXX500046209
IXX500046210
IXX500046211
IXX500046212
IXX500046214
IXX500046218
IXX500046219
IXX500046220
IXX500046221
IXX500046222
IXX500046224
IXX500046226
IXX500046227
IXX500046229
IXX500046230
IXX500046231
IXX500046232
IXX500046233
IXX500046234
IXX500046235
IXX500046236
IXX500046238
IXX500046239
IXX500046240
IXX500046241
IXX500046242
IXX500046247
IXX500046250
IXX500046255
IXX500046256
IXX500046257
IXX500046258
IXX500045877
IXX500045917
IXX500046033
IXX500046035
IXX500046082
IXX500046090
IXX500046091
IXX500046114
IXX500046123
IXX500046144
IXX500046181
IXX500046201
IXX500046216
IXX500046223
IXX500046237
IXX500046243
IXX500046246
IXX500046248
IXX500046251
IXX500046259
IXX500046263
IXX500046264
IXX500046265
IXX500046268
IXX500046269
IXX500046270
IXX500046271
IXX500046273
IXX500046274
IXX500046275
IXX500046277
IXX500046278
IXX500046281
IXX500046282
IXX500046284
IXX500046286
IXX500046288
IXX500046289
IXX500046290
IXX500046291
IXX500046293
IXX500046294
IXX500046295
IXX500046296
IXX500046297
IXX500046298
IXX500046299
IXX500046300
IXX500046301
IXX500046302
IXX500046303
IXX500046304
IXX500046306
IXX500046307
IXX500046308
IXX500046309
IXX500046310
IXX500046311
IXX500046312
IXX500046313
IXX500046314
IXX500046315
IXX500046316
IXX500046317
IXX500046318
IXX500046319
IXX500046320
IXX500046321
IXX500046322
IXX500046323
IXX500046324
IXX500046325
IXX500046326
IXX500046327
IXX500046328
IXX500046329
IXX500046331
IXX500046332
IXX500046334
IXX500046336
IXX500046338
IXX500046339
IXX500046340
IXX500046341
IXX500046342
IXX500046343
IXX500046344
IXX500046345
IXX500046347
IXX500046348
IXX500046350
IXX500046352
IXX500046353
IXX500046354
IXX500046355
IXX500046356
IXX500046357
IXX500046358
IXX500046359
IXX500046360
IXX500046361
IXX500046362
IXX500046363
IXX500046364
IXX500046367
IXX500046368
IXX500046369
IXX500046370
IXX500046371
IXX500046372
IXX500046373
IXX500046374
IXX500046378
IXX500046379
IXX500046380
IXX500046381
IXX500046382
IXX500046383
IXX500046384
IXX500046387
IXX500046388
IXX500046391
IXX500046394
IXX500046395
IXX500046396
IXX500046397
IXX500046398
IXX500046399
IXX500046400
IXX500046402
IXX500046403
IXX500046404
IXX500046405
IXX500046406
IXX500046407
IXX500046408
IXX500046409
IXX500046410
IXX500046411
IXX500046412
IXX500046413
IXX500046414
IXX500046415
IXX500046416
IXX500046418
IXX500046421
IXX500046422
IXX500046423
IXX500046424
IXX500046425
IXX500046426
IXX500046427
IXX500046428
IXX500046429
IXX500046430
IXX500046431
IXX500046432
IXX500046433
IXX500046434
IXX500046437
IXX500046438
IXX500046439
IXX500046441
IXX500046442
IXX500046443
IXX500046444
IXX500046445
IXX500046446
IXX500046449
IXX500046451
IXX500046455
IXX500046465
IXX500046469
IXX500046470";
$airbill_arr=explode("\r\n",$airbill_array);
$airbill_arr=implode($airbill_arr,"','");

	require_once('library/reference.php');

	//EDI mail
	require_once('library/globalfunction.php');
	require_once('library/class.phpmailer.php');

	require_once('commoninfo/shipmentbillinginfo.php');
	require_once('commoninfo/accessorialinfo.php');
	require_once('commoninfo/cusaddressinfo.php');
	require_once('commoninfo/shipmentinfo.php');

	require_once('bol/shipmentbillingbol.php');
	require_once('bol/importbillingbol.php');
	require_once('bol/accessorialbol.php');
	require_once('bol/shipmentbol.php');
	require_once('bol/eventlogbol.php');
	require_once('bol/webshipbol.php');
	require_once('bol/shipmenttypebol.php');
	require_once('bol/systemsettingsbol.php');
	require_once('bol/ratesheetbol.php');
	require_once('bol/customeraccessorialbol.php');
	require_once('bol/customerbol.php');
	require_once('bol/packagebol.php');

	require_once('dal/shipmentbillingdal.php');
	require_once('dal/importbillingdal.php');
	require_once('dal/accessorialdal.php');
	require_once('dal/shipmentdal.php');
	require_once('dal/eventlogdal.php');
	require_once('dal/webshipdal.php');
	require_once('dal/shipmenttypedal.php');
	require_once('dal/systemsettingsdal.php');
	require_once('dal/ratesheetdal.php');
	require_once('dal/customeraccessorialdal.php');
	require_once('dal/customerdal.php');
	require_once('dal/packagedal.php');

	require_once('bol/quoteratebol.php');
	require_once('dal/quoteratedal.php');
	require_once('bol/countrybol.php');
	require_once('dal/countrydal.php');
	require_once('bol/servicebol.php');
	require_once('dal/servicedal.php');

	require_once('dal/readonlyresultset.php');

	$dir="/var/www/xms/edi_file/tnt_et_file_dispose";	
	// $dir="edi_file/tnt_et_file_dispose";	
	// $dir="edi_file/tnt_et_file_dispose";	
	//setup_tnt_default_dir($dir."/");
	$trans_repair_id="";
	if(isset($_GET['trans_repair_id']))
		$trans_repair_id=$_GET['trans_repair_id'];
	if(isset($_GET['trans_repair_id']) || isset($argv[0]))
	{
		$et_msg="<br/>Fail to generate ET file.";
		$file=generate_ET_file_combine($airbill_arr);
	
		if(trim($file)!= "" && file_exists($dir."/".$file)){
			echo "<br/>Successful generate ET file ".$dir."/".$file;
			$ftp_return=tnt_et_file_dispose($file,$dir);
			if($ftp_return== "-1"){
				$et_msg= $file." ET file fail to dispose at FTP server. Can not login to FTP server.";
			}elseif($ftp_return== "0"){
				$et_msg= $file." ET file fail to dispose at FTP server.";
			}else{
				$et_msg= $file." ET file successfully disposed at FTP server.";
				$webshipbol =new webshipbol();
				$webshipbol->update_tnt_transmission_upload($file);
			}
			echo "\n".$et_msg;
		}
		
		$email="syaung09@gmail.com";
		$rtn = sendmail("","TNT Domestic ET file dispose",$et_msg,$email,true); 
		if(strlen($rtn) == 1)
			$rnt_message = 'Send Successfully.';
		else 
			$rnt_message = 'System can not send email. <br/>Please try again later.';
	}
	
	if(isset($_GET['tsmID'])){
		$smID = $_GET['tsmID'];
		$file=generate_ET_file_combine($airbill_arr);
		if(trim($file)!= ""){
			echo "<br/>successful generate ET file ".$dir."/".$file;
			//tnt_et_file_dispose($file,$dir);
		}
	}
	
	$files="";// "JAMESC30031055.dat,XMSGWT30031055.dat,";
	if(isset($_GET['files'])) $files = $_GET['files'];
	//echo "xfile=".$files;
	
	$fileary = explode(',',$files);
	foreach($fileary as $filename){
		//if(file_exists($filename)) echo "<br/>".$filename;
	}
	
	if(isset($_GET['dispose']) || isset($_GET['delete']) ){
		echo "<h2 style='font-size: 22px;'>TNT Domestic Dispose File</h2>
			<div class='frm' style='background: #6bab4b; color: #fff; width: 500px;'>&nbsp;</div>";
		 echo "<br/>";
	}
		 
	if(isset($_GET['dispose'])){
		$ftp_return=tnt_et_file_dispose($files,$dir);
		if($ftp_return== "-1"){
			$et_msg= $files." ET file fail to dispose at FTP server. Can not login to FTP server.";
		}elseif($ftp_return== "0"){
			$et_msg= $files." ET file fail to dispose at FTP server.";
		}else{
			$et_msg= $files." ET file successfully disposed at FTP server.";
			$webshipbol =new webshipbol();
			$webshipbol->update_tnt_transmission_upload($files);
		}
		echo $et_msg;
	}
	
	if(isset($_GET['delete'])){
		$ftp_return=tnt_et_file_delete($files,$dir);
		//echo "rt=".$ftp_return;
		if($ftp_return== "-1"){
			$et_msg= $files." ET file fail to delete at FTP server. Can not login to FTP server.";
		}elseif($ftp_return== "-2"){
			$et_msg= $files." ET file fail to delete at FTP server. File can not file";
		}elseif($ftp_return== "0"){
			$et_msg= $files." ET file fail to delete at FTP server.";
		}else{
			$et_msg= $files." ET file successfully deleted at FTP server.";
		}
		echo $et_msg;
	}
	
	function generate_ET_file_combine($airbill_arr,$dir=''){
		if($dir=="") $dir="/var/www/xms/edi_file/tnt_et_file_dispose";
		// if($dir=="") $dir="edi_file/tnt_et_file_dispose";
		$webshipbol =new webshipbol();
		$default_date_format = trim(selectglobalsettingvalue("Date Format"));
		$sender_id_code = trim(selectglobalsettingvalue("TNT Domestic Sender Identifier Code"));
		$ItemPrefix= trim(selectglobalsettingvalue('TNT Domestic Item Identifier Prefix'));;
		
		$servicecode_ary = array();
		$shipmenttypebol = new shipmenttypebol();
		$packagebol = new packagebol();
		$shipresult = $shipmenttypebol->selectshipmenttypeall();
		while($shiprow = $shipresult->getNext())
		{
			$servicecode_ary[$shiprow['shipment_type_id']]= $shiprow['service_code'];
		}	
		$packresult = $packagebol->getallpackages(2);
		$package_ary = array();
		while($packrow= $packresult->getNext())
		{
			$package_ary[$packrow['packageid']]= $packrow['packagename'];
		}
		
		$trading_partner = trim(selectglobalsettingvalue("TNT Domestic ET File Name"));
		if($trading_partner=="") $trading_partner="INXPAU"; //"JAMESCOOKUNI";
		$filename=  substr($trading_partner,0,6);
		
		$now_datetime = new DateTime();
		$now_date = $now_datetime->format("Y-m-d H:i:s");
		//echo "<br/>".$now_date;		
		$yyyyMMDD = $now_datetime->format("Ymd");
		//echo "\n<br/>".$yyyyMMDD;		
		$day_mon = $now_datetime->format("dm");
		//echo "\n<br/>".$now_date;		
		$hour_min = $now_datetime->format("Hi");
		//echo "\n<br/>".$now_date;		
		$filename .= $day_mon.$hour_min.".dat";
		// echo "\n<br/>".$filename;	
		//$filename="XMSGWT30031055.dat";
		
		$count = 0;
		$trans_repair_id="";
		if(isset($_GET['trans_repair_id']))
			$trans_repair_id=$_GET['trans_repair_id'];
		$tntresult = get_tnt_shipment_for_et_repair($airbill_arr);
		$iTotal = $tntresult->getFoundRows();
			echo "iTotal>>>>".$iTotal ;
		if($iTotal <=0 ) return "";
		
		$trans_ary=$webshipbol->save_tnt_transmission($iTotal) ;
		$trans_ary = explode('###',$trans_ary);
		$transmision_id = $trans_ary[0];
		$manifest_id = $trans_ary[1];
		$trans_id  = $trans_ary[2];
		if($trans_id=="0") return "";
		if($trans_id!="0"){
			$ret=$webshipbol->update_tnt_transmission($trans_id, $filename);
			//if($ret==true) return $filename;
		}

		$fp = fopen($dir."/".$filename, 'w');
		if(file_exists($dir."/".$filename) == false) return "";
		//$fp = "";
		//$filename="";
		
		$et_line_no=0;
		//3.1	Transmission Header (Type A) Record
		//Mandatory, 1 per transmission
		$A_Record_Type="A"; //CHAR	1 //Total Record Length		91
		$A_Transmission_Identifier=  $transmision_id; //"10000000000000000001"; //"TRANSMISSIONIDENTIFI"; //CHAR BF	20 
		$A_Transmission_Identifier=getRecord($A_Transmission_Identifier,"CHAR",20, "BF" ); //CHAR BF	20 
		$A_Sender_Interchange_Address= "               ";//"YangonnMYANMARR" ; //CHAR BF 15
		$A_Sender_Interchange_Address=getRecord($A_Sender_Interchange_Address,"CHAR",15, "BF" ); //CHAR BF 15
		$A_Receiver_Interchange_Address= "               ";//"Mandalay"; //CHAR BF 15
		$A_Receiver_Interchange_Address=getRecord($A_Receiver_Interchange_Address,"CHAR",15, "BF" );//CHAR BF 15
		$A_Trading_Partner_Identifier="";//"JAMESCOOKUNI"; //CHAR BF 12
		$A_Trading_Partner_Identifier=getRecord($A_Trading_Partner_Identifier,"CHAR",12, "BF" ); //CHAR BF 12
		$A_Carrier="TNT"; //CHAR 3
		$A_Carrier=getRecord($A_Carrier,"CHAR",3, "" );//CHAR 3
		$A_File_Generation_Date=$yyyyMMDD; //"20110804"; //CCYYMMDD  //CHAR 8
		$A_File_Generation_Date=getRecord($A_File_Generation_Date,"CHAR",8, "" ); //CHAR 8 
		$A_File_Generation_Time=$hour_min; //"1530"; //hhmm //CHAR 4
		$A_File_Generation_Time=getRecord($A_File_Generation_Time,"CHAR",4, "" ); //CHAR 4
		$A_File_Version_Number="12"; //CHAR 2
		$A_File_Version_Number=getRecord($A_File_Version_Number,"CHAR",2, "" ); //CHAR 2
		$A_Routing_Effective_Date="20110731"; //CHAR 8
		$A_Routing_Effective_Date=getRecord($A_Routing_Effective_Date,"CHAR",8, "" ); //CHAR 8
		$A_Routing_Version_Number="123"; //CHAR 3
		$A_Routing_Version_Number=getRecord($A_Routing_Version_Number,"CHAR",3, "" );  //CHAR 3 
		if($A_Record_Type!="NO" && $A_Transmission_Identifier!="NO" && $A_Sender_Interchange_Address!="NO" && $A_Receiver_Interchange_Address!="NO" && $A_Trading_Partner_Identifier!="NO" && $A_Carrier!="NO" && $A_File_Generation_Date!="NO" && $A_File_Generation_Time!="NO" && $A_File_Version_Number!="NO" && $A_Routing_Effective_Date!="NO" && $A_Routing_Version_Number!="NO")
		{
			$A_ROW=$A_Record_Type.$A_Transmission_Identifier.$A_Sender_Interchange_Address.$A_Receiver_Interchange_Address.$A_Trading_Partner_Identifier.$A_Carrier.$A_File_Generation_Date.$A_File_Generation_Time.$A_File_Version_Number.$A_Routing_Effective_Date.$A_Routing_Version_Number."\n";
			//echo "<br/>$A_ROW A_length:".strlen($A_ROW);
			fwrite($fp,$A_ROW);
			$et_line_no++;
		}
				
		$tntresult->reset();
		$manifest_index=0;
		$trans_repair_id="";
		if(isset($_GET['trans_repair_id']))
			$trans_repair_id=$_GET['trans_repair_id'];
		$tntresult = get_tnt_shipment_for_et_repair($airbill_arr);
		while($rowsender = $tntresult->getNext())
		{
			$manifest_id = $manifest_id + $manifest_index;
			$manifest_index=1;
			//echo "<br/>manifest_id=$manifest_id";
			
			$customer_code = $rowsender['customer_code'];
			$billing_type= $rowsender['billing_type'];
			$billing_account= $rowsender['billing_account'];
			$sender_companyname=$rowsender['companyname'] ;
			$sender_contactname=$rowsender['contact_name'] ;
			$sender_addr= $rowsender['address'] ;
			$sender_addr2= $rowsender['address2'] ;
			$sender_postalcode=$rowsender['postal_code'] ;
			$sender_city =$rowsender['city'] ;
			//$sender_countryname =$rowsender['countryname'] ;
			$sender_phone=$rowsender['phone'] ;
			$sender_state=$rowsender['state'] ;
			//echo "count=$count customer_code=$customer_code billing_type=$billing_type billing_account=$billing_account";
			
			$billing_type= $rowsender['billing_type']; //1->sender, 2->receiver, 3->third party
			$isThirdPartyAccount=false;
			if($billing_type==3)$isThirdPartyAccount=true;
			$billing_type_str="S";
			if($billing_type==2) $billing_type_str="R";
			if($billing_type==3) $billing_type_str="T";
		
			$trading_partner =str_ireplace(" ","", $sender_companyname); //"JAMESCOOKUNI";
				
			//Manifest FOR RECORD B
			//Mandatory, 1 per Sender Identifier Code/Account Number
			$B_Record_Type="B"; //CHAR 1  //Total Record Length		186
			$B_Manifest_Identifier= $manifest_id; //"1000000001"; //CHAR BF 10
			$B_Manifest_Identifier=getRecord($B_Manifest_Identifier,"CHAR",10, "BF" );//CHAR BF 10
			$B_Sender_Identifier_Code=$sender_id_code; //"INXPX          "; //CHAR BF 15
			$B_Sender_Identifier_Code=getRecord($B_Sender_Identifier_Code,"CHAR",15, "BF" ,STR_PAD_RIGHT);//CHAR BF 15
			
			$B_Sender_Account_Number=$billing_account; //"21766577  "; //CHAR BF 10
			if($billing_type_str!="S") $B_Sender_Account_Number="          ";
			
			$B_Sender_Account_Number=getRecord($B_Sender_Account_Number,"CHAR",10, "BF" ,STR_PAD_RIGHT);//CHAR BF 10
			$B_Sender_Name=$sender_companyname; //CHAR BF 30
			$B_Sender_Name=getRecord($B_Sender_Name,"CHAR",30, "BF" ,STR_PAD_RIGHT);//CHAR BF 30
			$B_Sender_Address_line1=$sender_addr; //CHAR BF 30
			$B_Sender_Address_line1=getRecord($B_Sender_Address_line1,"CHAR",30, "BF" ,STR_PAD_RIGHT);//CHAR BF 30
			$B_Sender_Address_line2=$sender_addr2; //CHAR BF 30
			$B_Sender_Address_line2=getRecord($B_Sender_Address_line2,"CHAR",30, "BF" ,STR_PAD_RIGHT);//CHAR BF 30
			$B_Sender_Suburb=$sender_city; //CHAR BF 20
			$B_Sender_Suburb=getRecord($B_Sender_Suburb,"CHAR",20, "BF" ,STR_PAD_RIGHT);//CHAR BF 20
			$B_Sender_State=$sender_state; //CHAR BF 3
			$B_Sender_State=getRecord($B_Sender_State,"CHAR",3, "BF" ,STR_PAD_RIGHT);//CHAR BF 10
			$B_Sender_Postcode=$sender_postalcode; //CHAR BF 4
			$B_Sender_Postcode=getRecord($B_Sender_Postcode,"CHAR",4, "BF" ,STR_PAD_RIGHT);//CHAR BF 4
			$B_Sender_Contact_Name=$sender_contactname; //CHAR BF 20
			$B_Sender_Contact_Name=getRecord($B_Sender_Contact_Name,"CHAR",20, "BF" ,STR_PAD_RIGHT);//CHAR BF 20
			$B_Sender_Contact_Phone=$sender_phone; //CHAR BF 13
			$B_Sender_Contact_Phone=getRecord($B_Sender_Contact_Phone,"CHAR",13, "BF" ,STR_PAD_RIGHT);//CHAR BF 13
			if($B_Record_Type!="NO" && $B_Manifest_Identifier!="NO" && $B_Sender_Identifier_Code!="NO" && $B_Sender_Account_Number!="NO" && $B_Sender_Name!="NO" && $B_Sender_Address_line1!="NO" && $B_Sender_Address_line2!="NO" && $B_Sender_Suburb!="NO" && $B_Sender_State!="NO" && $B_Sender_Postcode!="NO" && $B_Sender_Contact_Name!="NO" && $B_Sender_Contact_Phone!="NO")
			{
				$B_ROW=$B_Record_Type.$B_Manifest_Identifier.$B_Sender_Identifier_Code.$B_Sender_Account_Number.$B_Sender_Name.$B_Sender_Address_line1.$B_Sender_Address_line2.$B_Sender_Suburb.$B_Sender_State.$B_Sender_Postcode.$B_Sender_Contact_Name.$B_Sender_Contact_Phone."\n";
				//echo "<br/>$B_ROW B_length:".strlen($B_ROW);
				fwrite($fp,$B_ROW);
				$et_line_no++;
			}
				
			//$smID_arr=explode(',',$smID);
			$tnt_detailresult = get_tnt_shipment_detail_for_et_repair($customer_code, $billing_type , $billing_account ,$sender_companyname, $sender_contactname, $sender_phone, $sender_city, $sender_postalcode, $sender_state, $sender_addr, $sender_addr2 );
			while($rowdetail = $tnt_detailresult->getNext())
			//for($i=0;$i<count($smID_arr);$i++)
			{
				$smID = $rowdetail['shipmentid'];
				if($trans_id!="0" && $smID!=""){
					$ret=$webshipbol->update_tnt_transmission_connote($trans_id , $smID , $manifest_id);
				}
				
				// $shipresult=$webshipbol->getshipmentInfobyShipID_for_ET_file($smID,'');
				$shipresult=getshipmentInfobyShipID_for_ET_file($smID,'');
				$rowCou = $shipresult->getNext();
				$airbillno = $rowCou['airbill_number'];
				$awb_barcode = $rowCou['awb_barcode'];
				$dhlacc = $rowCou['dhl_international_account'];
				$sender_addrid= $rowCou['senderaddressid'];
				$receiver_addrid= $rowCou['receiveraddressid'];
				$shipmentid=$rowCou['shipmentid'];
				$no_of_pieces =$rowCou['no_of_pieces'];
				$shipmentdate= get_custom_date( $rowCou['shipment_date'],"dd-MM-yyyy");
				$shipmentdate = str_ireplace("-","", $shipmentdate);
				//echo "shipmentdate=".$shipmentdate;
				$content_description=$rowCou['content_description'];
				$currency_code=$rowCou['currency_code'];
				$weight_unit= strtoupper($rowCou['weight_unit']);
				$dimension_unit=strtoupper($rowCou['dimension_unit']);
				$productcontentcode =$rowCou['productcontentcode'];
				$service_area_code_destination =$rowCou['service_area_code_destination'];
				$service_area_code_origin =$rowCou['service_area_code_origin'];
				$customer_code=$rowCou['customer_code'];
				$termoftrade=$rowCou['term_of_trade'];
				$totalcustomvalue=$rowCou['total_custom_value'] ;
				$shipmenttype_id =$rowCou ['shipment_type_id'];
				$billing_account = $rowCou ['billing_account'];
				$shipreference  =  $rowCou ['reference'];
				$insuranceamt= $rowCou['withinsurance'];
				$voidstatus= $rowCou['status']; //1->void
				$isDangerousGood=false;
				$courier_message = $rowCou['courier_message'];
				if(trim($courier_message)=="DG") $isDangerousGood=true;
				
				$isSpecialInstruction=false;
				$isSpecialReference=false;
				$isAlertAddress=false;
				
				$reasonforexport  =  $rowCou['reasonforexport'];
				if(trim($reasonforexport)!="") $isSpecialInstruction=true;
				
				$service_code="76";
				if(isset($servicecode_ary[$shipmenttype_id])){
					$service_code= $servicecode_ary[$shipmenttype_id];
					$ary = explode(',',$service_code);
					$service_code = $ary[0];
					//echo "service_code=".$service_code;
				}
				

				
				$packagename="CARTON";
				$packageid= $rowCou['packageid'];
				if(isset($package_ary[$packageid]))
				$packagename= strtoupper($package_ary[$packageid]);
		
				//Sender Address//
				/*
				$senderresult=$webshipbol->getshipmentaddressbyid($sender_addrid);
				$rowsender = $senderresult->getNext();
				$sender_companyname=$rowsender['companyname'] ;
				$sender_contactname=$rowsender['contact_name'] ;
				$sender_addr= $rowsender['address'] ;
				$sender_addr2= $rowsender['address2'] ;
				$sender_postalcode=$rowsender['postal_code'] ;
				$sender_city =$rowsender['city'] ;
				$sender_countryname =$rowsender['countryname'] ;
				$sender_phone=$rowsender['phone'] ;
				$sender_state=$rowsender['state'] ;
				*/
				
				//Receiver Address//
				$recresult=$webshipbol->getshipmentaddressbyid($receiver_addrid);
				$rowreceiver = $recresult->getNext();
				$receiver_companyname=$rowreceiver['companyname'] ;
				$receiver_contactname=$rowreceiver['contact_name'] ;
				$receiver_addr=$rowreceiver['address'] ;
				$receiver_addr2=$rowreceiver['address2'] ;
				$receiver_postalcode=$rowreceiver['postal_code'] ;
				$receiver_city =$rowreceiver['city'] ;
				$receiver_phone=$rowreceiver['phone'] ;
				$receiver_countryname =$rowreceiver['countryname'] ;
				$receiver_state=$rowreceiver['state'] ;
				
				$piecesresult=$webshipbol->getshipmentpiecesbyairbill($shipmentid);
				$weight=0;$dimension_l=0;$dimension_w=0;$dimension_h=0;
				$pageindex=0;
				$totalweight=0;
				$F_item_ary=array();
				$item_identifier_ary=array();
				$pageindex=0;
				while ($rowpieces = $piecesresult->getNext()) 
				{
					$pageindex++;
					$l = $rowpieces['dimension_l'] ;
					$w = $rowpieces['dimension_w'] ;
					$h = $rowpieces['dimension_h'] ;
					$weight = $rowpieces['weight'] ;
					$ary=array("count"=>1 , "l"=>$l, "w"=>$w, "h"=>$h ,"weight"=>$weight );
					setItemgroup($ary, $F_item_ary);
					
					$item_tmp=getTNTItemIdentifier($ItemPrefix,$airbillno,$pageindex);	
					$item_identifier_ary[$pageindex] =$item_tmp;
				}
				//echo "<br/>xx".count($F_item_ary);
				//print_r($item_identifier_ary);
				
				$item_identifier_group_ary=array(0);
				$i=0;
				foreach($item_identifier_ary as $a){
					$i++;
					if($i>8){
						$item_identifier_group_ary[]=$i-1;
						$i=0;
					}
				}
				//print_r($item_identifier_group_ary);
				
				
				//Consignment FOR RECORD C
				//Mandatory, 1 per consignment
				$C_Record_Type="C";// CHAR	1  //Total Record Length		292
				$C_Manifest_Identifier=$manifest_id; //"1000000001";// CHAR BF	10
				$C_Manifest_Identifier=getRecord($C_Manifest_Identifier,"CHAR",10, "BF" );// CHAR BF	10
				$C_Consignment_Note_Number=$airbillno; //"IXX500000001   ";//CHAR BF	15
				$C_Consignment_Note_Number=getRecord($C_Consignment_Note_Number,"CHAR",15, "BF" ,STR_PAD_RIGHT );//CHAR BF	15
				$C_Lines_This_Consignment=count($F_item_ary); // "002"; //No of Items // NUM BF	3
				$C_Lines_This_Consignment=getRecord($C_Lines_This_Consignment,"NUM",3, "ZF" );// NUM BF	3
				$C_Receiver_Identifier_Code=""; //"L.SHEWAN       ";//CHAR BF	15
				$C_Receiver_Identifier_Code=getRecord($C_Receiver_Identifier_Code,"CHAR",15, "BF" ,STR_PAD_RIGHT);//CHAR BF	15
				$C_Receiver_Account_Number= $billing_account; //"          ";// CHAR BF	10
				if($billing_type_str!="R") $C_Receiver_Account_Number="          ";

				$C_Receiver_Account_Number=getRecord($C_Receiver_Account_Number,"CHAR",10, "BF" ,STR_PAD_RIGHT);// CHAR BF	10
				$C_Receiver_Name=$receiver_companyname;// CHAR BF	30
				$C_Receiver_Name=getRecord($C_Receiver_Name,"CHAR",30, "BF" ,STR_PAD_RIGHT);// CHAR BF	30
				$C_Receiver_Address_line1=$receiver_addr;// CHAR BF	30
				$C_Receiver_Address_line1=getRecord($C_Receiver_Address_line1,"CHAR",30, "BF" ,STR_PAD_RIGHT);// CHAR BF	30
				$C_Receiver_Address_line2=$receiver_addr2;//CHAR BF	30
				$C_Receiver_Address_line2=getRecord($C_Receiver_Address_line2,"CHAR",30, "BF" ,STR_PAD_RIGHT);//CHAR BF	30
				$C_Receiver_Suburb=$receiver_city;//CHAR BF	20
				$C_Receiver_Suburb=getRecord($C_Receiver_Suburb,"CHAR",20, "BF" ,STR_PAD_RIGHT);//CHAR BF	20
				$C_Receiver_State=$receiver_state;//CHAR BF	3
				$C_Receiver_State=getRecord($C_Receiver_State,"CHAR",3, "BF" ,STR_PAD_RIGHT );//CHAR BF	3
				$C_Receiver_Postcode=$receiver_postalcode;//CHAR BF	4
				$C_Receiver_Postcode=getRecord($C_Receiver_Postcode,"CHAR",4, "BF" ,STR_PAD_RIGHT);//CHAR BF	4
				$C_Receiver_Contact_Name=$receiver_contactname;//CHAR BF	20
				$C_Receiver_Contact_Name=getRecord($C_Receiver_Contact_Name,"CHAR",20, "BF" ,STR_PAD_RIGHT);//CHAR BF	20
				$C_Receiver_Contact_Phone=$receiver_phone;//CHAR BF	13
				$C_Receiver_Contact_Phone=getRecord($C_Receiver_Contact_Phone,"CHAR",13, "BF" ,STR_PAD_RIGHT);//CHAR BF	13
				$C_Con_Note_Date= $shipmentdate; //"03252012";//CHAR 	8
				$C_Con_Note_Date=getRecord($C_Con_Note_Date,"CHAR",8, "" );//CHAR 	8
				$C_Service=$service_code; //"76  ";//CHAR BF	4
				$C_Service=getRecord($C_Service,"CHAR",4, "BF" ,STR_PAD_RIGHT );//CHAR BF	4
				$C_Dangerous_Goods_Flag="0";//CHAR	1
				if($isDangerousGood==true) $C_Dangerous_Goods_Flag="1";
				$C_Dangerous_Goods_Flag=getRecord($C_Dangerous_Goods_Flag,"CHAR",1, "" );//CHAR	1
				$C_Payer_Flag=$billing_type_str; // "T";//CHAR	1
				
				$C_Payer_Flag=getRecord($C_Payer_Flag,"CHAR",1, "" );//CHAR	1
				$C_Cancelled_Flag="0";//CHAR	1
				$C_Cancelled_Flag=getRecord($C_Cancelled_Flag,"CHAR",1, "" );//CHAR	1
				$C_Foodstuffs_Flag="0";//CHAR	1
				$C_Foodstuffs_Flag=getRecord($C_Foodstuffs_Flag,"CHAR",1, "" );//CHAR	1
				
				$C_Extended_Warranty_Value= "0";//"00000000";//NUM BF	8
				if($insuranceamt>0) $C_Extended_Warranty_Value=$totalcustomvalue;//$insuranceamt;
				$C_Extended_Warranty_Value=getRecord($C_Extended_Warranty_Value,"NUM",8, "ZF" );//NUM BF	8
				$C_Extended_Warranty_Class=" ";//CHAR BF	1
				if($insuranceamt>0) $C_Extended_Warranty_Class='B';
				$C_Extended_Warranty_Class=getRecord($C_Extended_Warranty_Class,"CHAR",1, "BF" );//CHAR BF	1
				
				$C_Additional_Service1="   ";//CHAR BF	3
				$C_Additional_Service1=getRecord($C_Additional_Service1,"CHAR",3, "BF" );//CHAR BF	3
				$C_Additional_Service2="   ";//CHAR BF	3
				$C_Additional_Service2=getRecord($C_Additional_Service2,"CHAR",3, "BF" );//CHAR BF	3
				$C_Additional_Service3="   ";//CHAR BF	3
				$C_Additional_Service3=getRecord($C_Additional_Service3,"CHAR",3, "BF" );//CHAR BF	3
				$C_Additional_Service4="   ";//CHAR BF	3
				$C_Additional_Service4=getRecord($C_Additional_Service4,"CHAR",3, "BF" );//CHAR BF	3
				$C_Additional_Service5="   ";//CHAR BF	3
				$C_Additional_Service5=getRecord($C_Additional_Service5,"CHAR",3, "BF" );//CHAR BF	3
				$C_HandRate_Amount= 0.00; //15.48; //"000015.48";//NUM9.2 BF	9
				$C_HandRate_Amount=getRecord($C_HandRate_Amount,"NUM9.2",9, "ZF" );//NUM9.2 BF	9
				$C_Other_Charges= 0.00; //"000000.00";//NUM9.2 BF	9
				$C_Other_Charges=getRecord($C_Other_Charges,"NUM9.2",9, "ZF" );//NUM9.2 BF	9
				$C_Customer_Consignment_Reference="                              "; //CHAR BF	30
				$C_Customer_Consignment_Reference=getRecord($C_Customer_Consignment_Reference,"CHAR",30, "BF" ,STR_PAD_RIGHT);//CHAR BF	30
				if($C_Record_Type!="NO" && $C_Manifest_Identifier!="NO" && $C_Consignment_Note_Number!="NO" && $C_Lines_This_Consignment!="NO" && $C_Receiver_Identifier_Code!="NO" && $C_Receiver_Account_Number!="NO" && $C_Receiver_Name!="NO" && $C_Receiver_Address_line1!="NO" && $C_Receiver_Address_line2!="NO" && $C_Receiver_Suburb!="NO" && $C_Receiver_State!="NO" && $C_Receiver_Postcode!="NO" && $C_Receiver_Contact_Name!="NO" && $C_Receiver_Contact_Phone!="NO" && $C_Con_Note_Date!="NO" && $C_Service!="NO" && $C_Dangerous_Goods_Flag!="NO" && $C_Payer_Flag!="NO" && $C_Cancelled_Flag!="NO" && $C_Foodstuffs_Flag!="NO" && $C_Extended_Warranty_Value!="NO" && $C_Extended_Warranty_Class!="NO" && $C_Additional_Service1!="NO" && $C_Additional_Service2!="NO" && $C_Additional_Service3!="NO" && $C_Additional_Service4!="NO" && $C_Additional_Service5!="NO" && $C_HandRate_Amount!="NO" && $C_Other_Charges!="NO" && $C_Customer_Consignment_Reference!="NO")
				{	
					$C_ROW=$C_Record_Type.$C_Manifest_Identifier.$C_Consignment_Note_Number.$C_Lines_This_Consignment.$C_Receiver_Identifier_Code.$C_Receiver_Account_Number.$C_Receiver_Name.$C_Receiver_Address_line1.$C_Receiver_Address_line2.$C_Receiver_Suburb.$C_Receiver_State.$C_Receiver_Postcode.$C_Receiver_Contact_Name.$C_Receiver_Contact_Phone.$C_Con_Note_Date.$C_Service.$C_Dangerous_Goods_Flag.$C_Payer_Flag.$C_Cancelled_Flag.$C_Foodstuffs_Flag.$C_Extended_Warranty_Value.$C_Extended_Warranty_Class.$C_Additional_Service1.$C_Additional_Service2.$C_Additional_Service3.$C_Additional_Service4.$C_Additional_Service5.$C_HandRate_Amount.$C_Other_Charges.$C_Customer_Consignment_Reference."\n";
					//echo "<br/> C_length:".strlen($C_ROW);
					fwrite($fp,$C_ROW);
					$et_line_no++;
				}else{
					$C_ROW=$C_Record_Type.$C_Manifest_Identifier.$C_Consignment_Note_Number.$C_Lines_This_Consignment.$C_Receiver_Identifier_Code.$C_Receiver_Account_Number.$C_Receiver_Name.$C_Receiver_Address_line1.$C_Receiver_Address_line2.$C_Receiver_Suburb.$C_Receiver_State.$C_Receiver_Postcode.$C_Receiver_Contact_Name.$C_Receiver_Contact_Phone.$C_Con_Note_Date.$C_Service.$C_Dangerous_Goods_Flag.$C_Payer_Flag.$C_Cancelled_Flag.$C_Foodstuffs_Flag.$C_Extended_Warranty_Value.$C_Extended_Warranty_Class.$C_Additional_Service1.$C_Additional_Service2.$C_Additional_Service3.$C_Additional_Service4.$C_Additional_Service5.$C_HandRate_Amount.$C_Other_Charges.$C_Customer_Consignment_Reference."\n";
					//echo "<br/> XC_length:".strlen($C_ROW);
					fwrite($fp,$C_ROW);
					$et_line_no++;
				}
				
				//3.4	Third Party (Type D) Record
				//Optional, 1 per consignment where <C.Payer Flag> = “T”
				$D_Record_Type="D";//CHAR	1 //Total Record Length		168
				$D_Manifest_Identifier=$manifest_id; //"1000000001";//CHAR BF	10
				$D_Manifest_Identifier=getRecord($D_Manifest_Identifier,"CHAR",10, "BF" );//CHAR BF	10
				$D_Con_Note_Number=$airbillno; //"IXX500000001   ";//CHAR BF	15
				$D_Con_Note_Number=getRecord($D_Con_Note_Number,"CHAR",15, "BF" ,STR_PAD_RIGHT );//CHAR BF	15
				$D_Third_Party_Identifier_Code="";//CHAR BF	15
				$D_Third_Party_Identifier_Code=getRecord($D_Third_Party_Identifier_Code,"CHAR",15, "BF" ,STR_PAD_RIGHT);//CHAR BF	15
				$D_Third_Party_Account_Number=$billing_account; //"12345678  ";//CHAR BF	10
				$D_Third_Party_Account_Number=getRecord($D_Third_Party_Account_Number,"CHAR",10, "BF" ,STR_PAD_RIGHT);//CHAR BF	10
				$D_Third_Party_Name="Third Party Name";//CHAR BF	30
				$D_Third_Party_Name=getRecord($D_Third_Party_Name,"CHAR",30, "BF" ,STR_PAD_RIGHT);//CHAR BF	30
				$D_Third_Party_Address_line1="Third Party Address";//CHAR BF	30
				$D_Third_Party_Address_line1=getRecord($D_Third_Party_Address_line1,"CHAR",30, "BF",STR_PAD_RIGHT );//CHAR BF	30
				$D_Third_Party_Address_line2="";//CHAR BF	30
				$D_Third_Party_Address_line2=getRecord($D_Third_Party_Address_line2,"CHAR",30, "BF" ,STR_PAD_RIGHT);//CHAR BF	30
				$D_Third_Party_Suburb="Suburb";//CHAR BF	20
				$D_Third_Party_Suburb=getRecord($D_Third_Party_Suburb,"CHAR",20, "BF" ,STR_PAD_RIGHT);//CHAR BF	20
				$D_Third_Party_State="DSW";//CHAR BF	3
				$D_Third_Party_State=getRecord($D_Third_Party_State,"CHAR",3, "BF" ,STR_PAD_RIGHT);//CHAR BF	3
				$D_Third_Party_Postcode="1234";//CHAR BF	4
				$D_Third_Party_Postcode=getRecord($D_Third_Party_Postcode,"CHAR",4, "BF" ,STR_PAD_RIGHT);//CHAR BF	4
				if($D_Record_Type!="NO" && $D_Manifest_Identifier!="NO" && $D_Con_Note_Number!="NO" && $D_Third_Party_Identifier_Code!="NO" && $D_Third_Party_Account_Number!="NO" && $D_Third_Party_Name!="NO" && $D_Third_Party_Address_line1!="NO" && $D_Third_Party_Address_line2!="NO" && $D_Third_Party_Suburb!="NO" && $D_Third_Party_State!="NO" && $D_Third_Party_Postcode!="NO" )
				{	
					$D_ROW=$D_Record_Type.$D_Manifest_Identifier.$D_Con_Note_Number.$D_Third_Party_Identifier_Code.$D_Third_Party_Account_Number.$D_Third_Party_Name.$D_Third_Party_Address_line1.$D_Third_Party_Address_line2.$D_Third_Party_Suburb.$D_Third_Party_State.$D_Third_Party_Postcode."\n";
					//echo "<br/> D_length:".strlen($D_ROW);
					if($billing_type_str=="T"){ 
						fwrite($fp,$D_ROW);
						$et_line_no++;
					}
				}
				
				//$isSpecialInstruction=true;
				//3.5	Special Instructions and Customer Text (Type E) Record
				//Optional, up to 1 record per <E.Text Type> per consignment is allowed
				$E_Record_Type="E";//CHAR	1 //Total Record Length		107
				$E_Manifest_Identifier=$manifest_id; //"1000000001";//CHAR BF	10
				$E_Manifest_Identifier=getRecord($E_Manifest_Identifier,"CHAR",10, "BF" );//CHAR BF	10
				$E_Con_Note_Number=$airbillno; //"IXX500000001   ";//CHAR BF	15
				$E_Con_Note_Number=getRecord($E_Con_Note_Number,"CHAR",15, "BF" ,STR_PAD_RIGHT);//CHAR BF	15
				$E_Text_Type="0";//NUM	1
				/*
				“0” – print text in delivery special instructions box on consignment note
				“1”, “2”, “3” – print text as agreed with TNT, but not in the delivery or pickup special instructions boxes
				“4” – print text in pickup special instructions box on pickup labels/manifest
				*/
				$E_Text_Type=getRecord($E_Text_Type,"NUM",1, "" );//NUM	1
				//$E_Text_Line="FROM:DR.KATE DOMETT SCHOOL MEDICINE               07 4781 5608                  XX";//CHAR BF	80
				$E_Text_Line= $reasonforexport;//"FROM:".$B_Sender_Contact_Name.$B_Sender_Contact_Phone; 
				$E_Text_Line=getRecord($E_Text_Line,"CHAR",80, "BF" ,STR_PAD_RIGHT);//CHAR BF	80
				if($E_Record_Type!="NO" && $E_Manifest_Identifier!="NO" && $E_Con_Note_Number!="NO" && $E_Text_Type!="NO" && $E_Text_Line!="NO")
				{	
					$E_ROW=$E_Record_Type.$E_Manifest_Identifier.$E_Con_Note_Number.$E_Text_Type.$E_Text_Line."\n";
					//echo "<br/> E_length:".strlen($E_ROW);
					if($isSpecialInstruction==true){
						fwrite($fp,$E_ROW);
						$et_line_no++;
					}
				}
				
				
				
				$line_index=0;
				foreach($F_item_ary as $a){
					$l=$a['l']; $w=$a['w']; $h=$a['h'];
					$count= $a['count'];
					$weight= $a['weight'];
					$line_index++;
					
					if($dimension_unit=="IN"){ // 1 in = 2.54 cm
						$dimension_unit="CM";
						$l= $l * 2.54;
						$w= $w * 2.54;
						$h= $h * 2.54;
					}
					
					if($weight_unit=="LB"){ // 1 lb = 0.45359237 kg
						$weight_unit="KG";
						$weight = $weight * 0.45359237;
					}
										
					//3.6	Consignment Line (Type F) Record
					//Mandatory, 1 or more per consignment
					$F_Record_Type="F";//CHAR	1 ////Total Record Length	131
					$F_Manifest_Identifier=$manifest_id; //"1000000001";//CHAR BF	10
					$F_Manifest_Identifier=getRecord($F_Manifest_Identifier,"CHAR",10, "BF" );//CHAR BF	10
					$F_Con_Note_Number=$airbillno; //"IXX500000001   ";//CHAR BF	15
					$F_Con_Note_Number=getRecord($F_Con_Note_Number,"CHAR",15, "BF" ,STR_PAD_RIGHT );//CHAR BF	15
					$F_Line_Sequence=$line_index ;//NUM BF	3
					$F_Line_Sequence=getRecord($F_Line_Sequence,"NUM",3, "BF" ,STR_PAD_RIGHT );//NUM BF	3
					$F_Customer_Reference=$shipreference; //"6250.70292.0103";//CHAR BF	15
					if($line_index > 1) $F_Customer_Reference="";
					
					$F_Customer_Reference=getRecord($F_Customer_Reference,"CHAR",15, "BF" );//CHAR BF	15
					$F_Description_of_Goods=$packagename;//"SATCHEL             ";//CHAR BF	20
					$F_Description_of_Goods=getRecord($F_Description_of_Goods,"CHAR",20, "BF" ,STR_PAD_RIGHT );//CHAR BF	20
					$F_Commodity_Code="      ";//CHAR BF	6
					$F_Commodity_Code=getRecord($F_Commodity_Code,"CHAR",6, "BF" );//CHAR BF	6
					$F_Number_of_Items= $count; //"00001";//NUM BF	5
					$F_Number_of_Items=getRecord($F_Number_of_Items,"NUM",5, "ZF" );//NUM BF	5
					$F_Weight= number_format($weight ,3,'.','') ; //00001.000;//NUM9.3 BF	9
					$F_Weight=getRecord($F_Weight,"NUM9.3",9, "ZF" );//NUM9.3 BF	9
					$F_Unit_of_Measure_Weight=$weight_unit;//CHAR BF	2
					$F_Unit_of_Measure_Weight=getRecord($F_Unit_of_Measure_Weight,"CHAR",2, "BF" );//CHAR BF	2
					$F_Item_Dimension_Length=number_format($l ,2,'.',''); // 0010.00;//NUM7.3 BF	7
					$F_Item_Dimension_Length=getRecord($F_Item_Dimension_Length,"NUM7.2",7, "ZF" );//NUM7.3 BF	7
					$F_Item_Dimension_Width=number_format($w ,2,'.',''); // 0010.00;//NUM7.3 BF	7
					$F_Item_Dimension_Width=getRecord($F_Item_Dimension_Width,"NUM7.2",7, "ZF" );//NUM7.3 BF	7
					$F_Item_Dimension_Height=number_format($h ,2,'.',''); // 0010.00;//NUM7.3 BF	7
					$F_Item_Dimension_Height=getRecord($F_Item_Dimension_Height,"NUM7.2",7, "ZF" );//NUM7.3 BF	7
					$F_Unit_of_Measure_Dimension=$dimension_unit;//CHAR BF	2
					$F_Unit_of_Measure_Dimension=getRecord($F_Unit_of_Measure_Dimension,"CHAR",2, "BF" );//CHAR BF	2
					
					//formula >>  1 cm = 0.01m >> vol 1 cm3  = 0.000001
					$vol = 0.000001 * $l * $w * $h * $count;
					
					$F_Cubic_Volume=number_format($vol ,3,'.',''); //0.0130; // 00000.0130;//NUM10.4 BF	10
					$F_Cubic_Volume=getRecord($F_Cubic_Volume,"NUM10.4",10, "ZF" );//NUM10.4 BF	10
					$F_Unit_of_Measure_Cubic_Volume="CO";//CHAR BF	2
					$F_Unit_of_Measure_Cubic_Volume=getRecord($F_Unit_of_Measure_Cubic_Volume,"CHAR",2, "BF" );//CHAR BF	2
					$F_Article_Qty=0;//"00000";//NUM BF	5
					$F_Article_Qty=getRecord($F_Article_Qty,"NUM",5, "ZF" );//NUM BF	5
					$F_Garment_Qty=0;//"00000";//NUM BF	5
					$F_Garment_Qty=getRecord($F_Garment_Qty,"NUM",5, "ZF" );//NUM BF	5
					if($F_Record_Type!="NO" && $F_Manifest_Identifier!="NO" && $F_Con_Note_Number!="NO" && $F_Line_Sequence!="NO" && $F_Customer_Reference!="NO" && $F_Description_of_Goods!="NO" && $F_Commodity_Code!="NO" && $F_Number_of_Items!="NO" && $F_Weight!="NO" && $F_Unit_of_Measure_Weight!="NO" && $F_Item_Dimension_Length!="NO" &&  $F_Item_Dimension_Width!="NO" && $F_Item_Dimension_Height!="NO" && $F_Unit_of_Measure_Dimension!="NO" && $F_Cubic_Volume!="NO" && $F_Unit_of_Measure_Cubic_Volume!="NO" && $F_Article_Qty!="NO" && $F_Garment_Qty!="NO")
					{	
						$F_ROW=$F_Record_Type.$F_Manifest_Identifier.$F_Con_Note_Number.$F_Line_Sequence.$F_Customer_Reference.$F_Description_of_Goods.$F_Commodity_Code.$F_Number_of_Items.$F_Weight.$F_Unit_of_Measure_Weight.$F_Item_Dimension_Length. $F_Item_Dimension_Width.$F_Item_Dimension_Height.$F_Unit_of_Measure_Dimension.$F_Cubic_Volume.$F_Unit_of_Measure_Cubic_Volume.$F_Article_Qty.$F_Garment_Qty."\n";
						//echo "<br/> F_length:".strlen($F_ROW);
						fwrite($fp,$F_ROW);
						$et_line_no++;
					}
				}
					
				//	Dangerous Goods FOR G RECORD
				//Optional, data from this record is currently not imported in to the FMS database
				$G_Record_Type="G";//CHAR	1 // Total Record Length 147
				$G_Manifest_Identifier="Manifest";//CHAR BF	10
				$G_Manifest_Identifier=getRecord($G_Manifest_Identifier,"CHAR",10, "BF" );//CHAR BF	10
				$G_Con_Note_Number=$airbillno;//"Con Note";//CHAR BF	15
				$G_Con_Note_Number=getRecord($G_Con_Note_Number,"CHAR",15, "BF" );//CHAR BF	15
				$G_Line_Sequence="001";///111;//NUM BF	3
				$G_Line_Sequence=getRecord($G_Line_Sequence,"NUM",3, "BF" );//NUM BF	3
				$G_Product_Sequence='01';//22;//NUM BF	2
				$G_Product_Sequence=getRecord($G_Product_Sequence,"NUM",2, "BF" );//NUM BF	2
				$G_Product_Weight=$F_Weight;//333333333;//NUM9.3 BF	9
				$G_Product_Weight=getRecord($G_Product_Weight,"NUM9.3",9, "BF" );//NUM9.3 BF	9
				$G_Unit_of_Measure_Weight="KG";//CHAR BF	2
				$G_Unit_of_Measure_Weight=getRecord($G_Unit_of_Measure_Weight,"CHAR",2, "BF" );//CHAR BF	2
				$G_Haz_Material_Type="c";//CHAR BF	1
				$G_Haz_Material_Type=getRecord($G_Haz_Material_Type,"CHAR",1, "BF" );//CHAR BF	1
				$G_Haz_Material_Code="Haz";//CHAR BF	4
				$G_Haz_Material_Code=getRecord($G_Haz_Material_Code,"CHAR",4, "BF" );//CHAR BF	4
				$G_Haz_Material_Technical_Name="e80";//CHAR BF	80
				$G_Haz_Material_Technical_Name=getRecord($G_Haz_Material_Technical_Name,"CHAR",80, "BF" );//CHAR BF	80
				$G_Principle_Risk_Class="Principle";//CHAR BF	10
				$G_Principle_Risk_Class=getRecord($G_Principle_Risk_Class,"CHAR",10, "BF" );//CHAR BF	10
				$G_Packaging_Group_Code="Pack";//CHAR BF	10
				$G_Packaging_Group_Code=getRecord($G_Packaging_Group_Code,"CHAR",10, "BF" );//CHAR BF	10
				if($G_Record_Type!="NO" && $G_Manifest_Identifier!="NO" && $G_Con_Note_Number!="NO" && $G_Line_Sequence!="NO" && $G_Product_Sequence!="NO" && $G_Product_Weight!="NO" && $G_Unit_of_Measure_Weight!="NO" && $G_Haz_Material_Type!="NO" && $G_Haz_Material_Code!="NO" && $G_Haz_Material_Technical_Name!="NO" && $G_Principle_Risk_Class!="NO" && $G_Packaging_Group_Code!="NO")
				{	
					$G_ROW=$G_Record_Type.$G_Manifest_Identifier.$G_Con_Note_Number.$G_Line_Sequence.$G_Product_Sequence.$G_Product_Weight.$G_Unit_of_Measure_Weight.$G_Haz_Material_Type.$G_Haz_Material_Code.$G_Haz_Material_Technical_Name.$G_Principle_Risk_Class.$G_Packaging_Group_Code."\n";
					//echo "<br/>  G_length:".strlen($G_ROW);
					if($isDangerousGood==true){
						fwrite($fp,$G_ROW);
						$et_line_no++;
					}
				} 

				$index=0;
				foreach($item_identifier_group_ary as $a) {
					$index++;
					//3.8	Item Identifiers (Type H) Record
					//Mandatory, 1 or more per consignment
					$H_Record_Type="H";//CHAR	1 // Total Record Length 189
					$H_Manifest_Identifier=$manifest_id; //"1000000001";//CHAR BF	10
					$H_Manifest_Identifier=getRecord($H_Manifest_Identifier,"CHAR",10, "BF" );//CHAR BF	10
					$H_Con_Note_Number=$airbillno; //"IXX500000001   ";//CHAR BF	15
					//$H_Con_Note_Number="IXX500000001   ";//CHAR BF	15
					$H_Con_Note_Number=getRecord($H_Con_Note_Number,"CHAR",15, "BF" ,STR_PAD_RIGHT);//CHAR BF	15
					$H_Sequence=$index; //"001";//NUM BF	3
					$H_Sequence=getRecord($H_Sequence,"NUM",3, "ZF" );//NUM BF	3
					
					$item_id="";
					if(isset($item_identifier_ary[1+$a])) $item_id=$item_identifier_ary[1+$a];
					$H_Item_Identifier_1_9=$item_id; //"00183333500000001001";//CHAR BF	20
					$H_Item_Identifier_1_9=getRecord($H_Item_Identifier_1_9,"CHAR",20, "BF" );//CHAR BF	20
					
					$item_id="";
					if(isset($item_identifier_ary[2+$a])) $item_id=$item_identifier_ary[2+$a];
					$H_Item_Identifier_2_10=$item_id; //"00183333500000001002";//CHAR BF	20
					$H_Item_Identifier_2_10=getRecord($H_Item_Identifier_2_10,"CHAR",20, "BF" );//CHAR BF	20
					
					$item_id="";
					if(isset($item_identifier_ary[3+$a])) $item_id=$item_identifier_ary[3+$a];
					$H_Item_Identifier_3_11=$item_id; //"                    ";//CHAR BF	20
					$H_Item_Identifier_3_11=getRecord($H_Item_Identifier_3_11,"CHAR",20, "BF" );//CHAR BF	20
					
					$item_id="";
					if(isset($item_identifier_ary[4+$a])) $item_id=$item_identifier_ary[4+$a];
					$H_Item_Identifier_4_12=$item_id; //"                    ";//CHAR BF	20
					$H_Item_Identifier_4_12=getRecord($H_Item_Identifier_4_12,"CHAR",20, "BF" );//CHAR BF	20
					
					$item_id="";
					if(isset($item_identifier_ary[5+$a])) $item_id=$item_identifier_ary[5+$a];
					$H_Item_Identifier_5_13=$item_id; //"                    ";//CHAR BF	20
					$H_Item_Identifier_5_13=getRecord($H_Item_Identifier_5_13,"CHAR",20, "BF" );//CHAR BF	20
					
					$item_id="";
					if(isset($item_identifier_ary[6+$a])) $item_id=$item_identifier_ary[6+$a];
					$H_Item_Identifier_6_14=$item_id; //"                    ";//CHAR BF	20
					$H_Item_Identifier_6_14=getRecord($H_Item_Identifier_6_14,"CHAR",20, "BF" );//CHAR BF	20
					
					$item_id="";
					if(isset($item_identifier_ary[7+$a])) $item_id=$item_identifier_ary[7+$a];
					$H_Item_Identifier_7_15=$item_id; //"                    ";//CHAR BF	20
					$H_Item_Identifier_7_15=getRecord($H_Item_Identifier_7_15,"CHAR",20, "BF" );//CHAR BF	20
					
					$item_id="";
					if(isset($item_identifier_ary[8+$a])) $item_id=$item_identifier_ary[8+$a];
					$H_Item_Identifier_8_16=$item_id; //"                    ";//CHAR BF	20
					$H_Item_Identifier_8_16=getRecord($H_Item_Identifier_8_16,"CHAR",20, "BF" );//CHAR BF	20
					if($H_Record_Type!="NO" && $H_Manifest_Identifier!="NO" && $H_Con_Note_Number!="NO" && $H_Sequence!="NO" && $H_Item_Identifier_1_9!="NO" && $H_Item_Identifier_2_10!="NO" && $H_Item_Identifier_3_11!="NO" && $H_Item_Identifier_4_12!="NO" && $H_Item_Identifier_5_13!="NO" && $H_Item_Identifier_6_14!="NO" && $H_Item_Identifier_7_15!="NO" && $H_Item_Identifier_8_16!="NO")
					{	
						$H_ROW=$H_Record_Type.$H_Manifest_Identifier.$H_Con_Note_Number.$H_Sequence.$H_Item_Identifier_1_9.$H_Item_Identifier_2_10.$H_Item_Identifier_3_11.$H_Item_Identifier_4_12.$H_Item_Identifier_5_13.$H_Item_Identifier_6_14.$H_Item_Identifier_7_15.$H_Item_Identifier_8_16."\n";
						//echo "<br/>  H_length:".strlen($H_ROW);
						fwrite($fp,$H_ROW);
						$et_line_no++;
					}
				}



				// 
				//3.9	Alert Address (Type J) Record
				//Optional, 0 up to 999 per consignment
				$J_Record_Type="J";//CHAR	1 //Total Record Length		109
				$J_Manifest_Identifier=$manifest_id; //"1000000001";//CHAR BF	10
				$J_Manifest_Identifier=getRecord($J_Manifest_Identifier,"CHAR",10, "BF" );//CHAR BF	10
				$J_Con_Note_Number=$airbillno; //"IXX500000001   ";//CHAR BF	15
				$J_Con_Note_Number=getRecord($J_Con_Note_Number,"CHAR",15, "BF" ,STR_PAD_RIGHT );//CHAR BF	15
				$J_Alert_Type="OP";//CHAR BF	2
				$J_Alert_Type=getRecord($J_Alert_Type,"CHAR",2, "BF" );//CHAR BF	2
				$J_Address_Type="E";//CHAR BF	1
				$J_Address_Type=getRecord($J_Address_Type,"CHAR",1, "BF" );//CHAR BF	1
				$J_Address="tui.adams1@jcu.edu.au                                                           ";//CHAR BF	80
				$J_Address=getRecord($J_Address,"CHAR",80, "BF" );//CHAR BF	80
				if($J_Record_Type!="NO" && $J_Manifest_Identifier!="NO" && $J_Con_Note_Number!="NO" && $J_Alert_Type!="NO" && $J_Address_Type!="NO" && $J_Address!="NO")
				{	
					$J_ROW=$J_Record_Type.$J_Manifest_Identifier.$J_Con_Note_Number.$J_Alert_Type.$J_Address_Type.$J_Address."\n";
					//echo "<br/> J_length:".strlen($J_ROW);
					if($isAlertAddress==true){
						fwrite($fp,$J_ROW);
						$et_line_no++;
					}
				}

				//3.10	Special Reference (Type S) Record
				//Optional, 0 up to 999 per consignment
				$newtext = wordwrap($shipreference, 8, "\n" , true);
				$ref_ary = explode("\n",$newtext);
				foreach($ref_ary as $text){
					$text=trim($text);
					if($text=="") continue;
					//Special Reference FOR S RECORD
					//Optional, 0 up to 999 per consignment
					$S_Record_Type="S";//CHAR	1 // Total Record Length		34
					$S_Manifest_Identifier=$manifest_id; //"1000000001";//CHAR BF	10
					$S_Manifest_Identifier=getRecord($S_Manifest_Identifier,"CHAR",10, "BF" );
					$S_Con_Note_Number=$airbillno; //"IXX500000001   ";//CHAR BF	15
					$S_Con_Note_Number=getRecord($S_Con_Note_Number,"CHAR",15, "BF" ,STR_PAD_RIGHT );
					$S_Special_Reference_Text =$text; // "LEAVE     ";//CHAR BF	8
					$S_Special_Reference_Text=getRecord($S_Special_Reference_Text,"CHAR",8, "BF" ,STR_PAD_RIGHT);
					if($S_Record_Type!="NO" && $S_Manifest_Identifier!="NO" && $S_Con_Note_Number!="NO" && $S_Special_Reference_Text!="NO")
					{	
						$S_ROW=$S_Record_Type.$S_Manifest_Identifier.$S_Con_Note_Number.$S_Special_Reference_Text."\n";
						//echo "<br/> S_length:".strlen($S_ROW);
						fwrite($fp,$S_ROW);
						$et_line_no++;
					}
				}

			}
		}

		$et_line_no++;
		//Transmission Trailer FOR Z RECORD
		//Mandatory, 1 per transmission
		$Z_Record_Type="Z";//CHAR	1 // Total Record Length 26
		$Z_Transmission_Identifier= $transmision_id; //"10000000000000000001";//CHAR BF	20
		$Z_Transmission_Identifier=getRecord($Z_Transmission_Identifier,"CHAR",20,"BF" );
		$Z_Record_Check_count=$et_line_no; //67;//00067;//NUM BF	5 
		$Z_Record_Check_count=getRecord($Z_Record_Check_count,"NUM",5,"ZF" );
		if($Z_Record_Type!="NO" && $Z_Transmission_Identifier!="NO" && $Z_Record_Check_count!="NO")
		{	
			$Z_ROW=$Z_Record_Type.$Z_Transmission_Identifier.$Z_Record_Check_count."\n";
			//echo "<br/> Z_length:".strlen($Z_ROW);
			fwrite($fp,$Z_ROW);
		}
				
		fclose($fp); 
		return $filename;
	}	
	
	function generate_ET_file($smID,$dir=''){
		if($dir=="") $dir="/var/www/xms/edi_file/tnt_et_file_dispose";
		// if($dir=="") $dir="edi_file/tnt_et_file_dispose";
		
		$webshipbol =new webshipbol();
		$default_date_format = trim(selectglobalsettingvalue("Date Format"));
		$sender_id_code = trim(selectglobalsettingvalue("TNT Domestic Sender Identifier Code"));
		$ItemPrefix= trim(selectglobalsettingvalue('TNT Domestic Item Identifier Prefix'));;
		
		$servicecode_ary = array();
		$shipmenttypebol = new shipmenttypebol();
		$packagebol = new packagebol();
		$shipresult = $shipmenttypebol->selectshipmenttypeall();
		while($shiprow = $shipresult->getNext())
		{
			$servicecode_ary[$shiprow['shipment_type_id']]= $shiprow['service_code'];
		}	
		$packresult = $packagebol->getallpackages(2);
		$package_ary = array();
		while($packrow= $packresult->getNext())
		{
			$package_ary[$packrow['packageid']]= $packrow['packagename'];
		}
				
		$trans_ary=$webshipbol->save_tnt_transmission() ;
		$trans_ary = explode('###',$trans_ary);
		$transmision_id = $trans_ary[0];
		$manifest_id = $trans_ary[1];
		$trans_id  = $trans_ary[2];
		if($trans_id=="0") return "";
		
		$trading_partner="JAMESCOOKUNI";
		$filename= substr($trading_partner,0,6);
		
		$now_datetime = new DateTime();
		$now_date = $now_datetime->format("Y-m-d H:i:s");
		//echo "<br/>".$now_date;		
		$yyyyMMDD = $now_datetime->format("Ymd");
		//echo "\n<br/>".$yyyyMMDD;		
		$day_mon = $now_datetime->format("dm");
		//echo "\n<br/>".$now_date;		
		$hour_min = $now_datetime->format("Hi");
		//echo "\n<br/>".$now_date;		
		$filename .= $day_mon.$hour_min.".dat";
		//echo "\n<br/>".$filename;	
		$filename="XMSGWT30031055.dat";
		
		
		//$fp = fopen($dir."/".$filename, 'w');
		$fp = "";
		$filename="";
		if($smID !='')
		{
			$smID_arr=explode(',',$smID);
			for($i=0;$i<count($smID_arr);$i++)
			{
				//$shipresult=$webshipbol->getshipmentInfobyShipID_for_ET_file($smID_arr[$i],'');
				$shipresult=getshipmentInfobyShipID_for_ET_file($smID_arr[$i],'');
				$rowCou = $shipresult->getNext();
				$airbillno = $rowCou['airbill_number'];
				$awb_barcode = $rowCou['awb_barcode'];
				$dhlacc = $rowCou['dhl_international_account'];
				$sender_addrid= $rowCou['senderaddressid'];
				$receiver_addrid= $rowCou['receiveraddressid'];
				$shipmentid=$rowCou['shipmentid'];
				$no_of_pieces =$rowCou['no_of_pieces'];
				$shipmentdate= get_custom_date( $rowCou['shipment_date'],"dd-MM-yyyy");
				$shipmentdate = str_ireplace("-","", $shipmentdate);
				//echo "shipmentdate=".$shipmentdate;
				$content_description=$rowCou['content_description'];
				$currency_code=$rowCou['currency_code'];
				$weight_unit= strtoupper($rowCou['weight_unit']);
				$dimension_unit=strtoupper($rowCou['dimension_unit']);
				$productcontentcode =$rowCou['productcontentcode'];
				$service_area_code_destination =$rowCou['service_area_code_destination'];
				$service_area_code_origin =$rowCou['service_area_code_origin'];
				$customer_code=$rowCou['customer_code'];
				$termoftrade=$rowCou['term_of_trade'];
				$totalcustomvalue=$rowCou['total_custom_value'] ;
				$shipmenttype_id =$rowCou ['shipment_type_id'];
				$billing_account = $rowCou ['billing_account'];
				$shipreference  =  $rowCou ['reference'];
				$insuranceamt= $rowCou['withinsurance'];
				$voidstatus= $rowCou['status']; //1->void
				$isDangerousGood=false;
				$courier_message = $rowCou['courier_message'];
				if(trim($courier_message)=="DG") $isDangerousGood=true;
				
				$billing_type= $rowCou['billing_type']; //1->sender, 2->receiver, 3->third party
				$isThirdPartyAccount=false;
				if($billing_type==3)$isThirdPartyAccount=true;
				$billing_type_str="S";
				if($billing_type==2) $billing_type_str="R";
				if($billing_type==3) $billing_type_str="T";
				
				$isSpecialInstruction=false;
				$isSpecialReference=false;
				$isAlertAddress=false;
				
				$service_code="76";
				if(isset($servicecode_ary[$shipmenttype_id])){
					$service_code= $servicecode_ary[$shipmenttype_id];
					$ary = explode(',',$service_code);
					$service_code = $ary[0];
					//echo "service_code=".$service_code;
				}
				

				
				$packagename="CARTON";
				$packageid= $rowCou['packageid'];
				if(isset($package_ary[$packageid]))
				$packagename= strtoupper($package_ary[$packageid]);
		
				//Sender Address//
				$senderresult=$webshipbol->getshipmentaddressbyid($sender_addrid);
				$rowsender = $senderresult->getNext();
				$sender_companyname=$rowsender['companyname'] ;
				$sender_contactname=$rowsender['contact_name'] ;
				$sender_addr= $rowsender['address'] ;
				$sender_addr2= $rowsender['address2'] ;
				$sender_postalcode=$rowsender['postal_code'] ;
				$sender_city =$rowsender['city'] ;
				$sender_countryname =$rowsender['countryname'] ;
				$sender_phone=$rowsender['phone'] ;
				$sender_state=$rowsender['state'] ;
				
				//Receiver Address//
				$recresult=$webshipbol->getshipmentaddressbyid($receiver_addrid);
				$rowreceiver = $recresult->getNext();
				$receiver_companyname=$rowreceiver['companyname'] ;
				$receiver_contactname=$rowreceiver['contact_name'] ;
				$receiver_addr=$rowreceiver['address'] ;
				$receiver_addr2=$rowreceiver['address2'] ;
				$receiver_postalcode=$rowreceiver['postal_code'] ;
				$receiver_city =$rowreceiver['city'] ;
				$receiver_phone=$rowreceiver['phone'] ;
				$receiver_countryname =$rowreceiver['countryname'] ;
				$receiver_state=$rowreceiver['state'] ;
				
				$piecesresult=$webshipbol->getshipmentpiecesbyairbill($shipmentid);
				$weight=0;$dimension_l=0;$dimension_w=0;$dimension_h=0;
				$pageindex=0;
				$totalweight=0;
				$F_item_ary=array();
				$item_identifier_ary=array();
				$pageindex=0;
				while ($rowpieces = $piecesresult->getNext()) 
				{
					$pageindex++;
					$l = $rowpieces['dimension_l'] ;
					$w = $rowpieces['dimension_w'] ;
					$h = $rowpieces['dimension_h'] ;
					$weight = $rowpieces['weight'] ;
					$ary=array("count"=>1 , "l"=>$l, "w"=>$w, "h"=>$h ,"weight"=>$weight );
					setItemgroup($ary, $F_item_ary);
					
					$item_tmp=getTNTItemIdentifier($ItemPrefix,$airbillno,$pageindex);	
					$item_identifier_ary[$pageindex] =$item_tmp;
				}
				//echo "<br/>xx".count($F_item_ary);
				//print_r($item_identifier_ary);
				
				$item_identifier_group_ary=array(0);
				$i=0;
				foreach($item_identifier_ary as $a){
					$i++;
					if($i>8){
						$item_identifier_group_ary[]=$i-1;
						$i=0;
					}
				}
				//print_r($item_identifier_group_ary);
				
				$trading_partner =str_ireplace(" ","", $sender_companyname); //"JAMESCOOKUNI";
				if($filename==""){
					$filename=strtoupper( substr($trading_partner,0,4) );
					//echo $filename.$day_mon.$hour_min.".dat";
					$filename= gettntfilename($dir."/" ,$filename, $day_mon.$hour_min ,".dat");
					//$filename="XMSGWT30031055.dat";
					$fp = fopen($dir."/".$filename, 'a');
					//$fp = fopen($filename, 'a');
				}
		
				$et_line_no=0;
				//3.1	Transmission Header (Type A) Record
				//Mandatory, 1 per transmission
				$A_Record_Type="A"; //CHAR	1 //Total Record Length		91
				$A_Transmission_Identifier=  $transmision_id; //"10000000000000000001"; //"TRANSMISSIONIDENTIFI"; //CHAR BF	20 
				$A_Transmission_Identifier=getRecord($A_Transmission_Identifier,"CHAR",20, "BF" ); //CHAR BF	20 
				$A_Sender_Interchange_Address= "               ";//"YangonnMYANMARR" ; //CHAR BF 15
				$A_Sender_Interchange_Address=getRecord($A_Sender_Interchange_Address,"CHAR",15, "BF" ); //CHAR BF 15
				$A_Receiver_Interchange_Address= "               ";//"Mandalay"; //CHAR BF 15
				$A_Receiver_Interchange_Address=getRecord($A_Receiver_Interchange_Address,"CHAR",15, "BF" );//CHAR BF 15
				$A_Trading_Partner_Identifier="";//"JAMESCOOKUNI"; //CHAR BF 12
				$A_Trading_Partner_Identifier=getRecord($A_Trading_Partner_Identifier,"CHAR",12, "BF" ); //CHAR BF 12
				$A_Carrier="TNT"; //CHAR 3
				$A_Carrier=getRecord($A_Carrier,"CHAR",3, "" );//CHAR 3
				$A_File_Generation_Date=$yyyyMMDD; //"20110804"; //CCYYMMDD  //CHAR 8
				$A_File_Generation_Date=getRecord($A_File_Generation_Date,"CHAR",8, "" ); //CHAR 8 
				$A_File_Generation_Time=$hour_min; //"1530"; //hhmm //CHAR 4
				$A_File_Generation_Time=getRecord($A_File_Generation_Time,"CHAR",4, "" ); //CHAR 4
				$A_File_Version_Number="12"; //CHAR 2
				$A_File_Version_Number=getRecord($A_File_Version_Number,"CHAR",2, "" ); //CHAR 2
				$A_Routing_Effective_Date="20110731"; //CHAR 8
				$A_Routing_Effective_Date=getRecord($A_Routing_Effective_Date,"CHAR",8, "" ); //CHAR 8
				$A_Routing_Version_Number="123"; //CHAR 3
				$A_Routing_Version_Number=getRecord($A_Routing_Version_Number,"CHAR",3, "" );  //CHAR 3 
				if($A_Record_Type!="NO" && $A_Transmission_Identifier!="NO" && $A_Sender_Interchange_Address!="NO" && $A_Receiver_Interchange_Address!="NO" && $A_Trading_Partner_Identifier!="NO" && $A_Carrier!="NO" && $A_File_Generation_Date!="NO" && $A_File_Generation_Time!="NO" && $A_File_Version_Number!="NO" && $A_Routing_Effective_Date!="NO" && $A_Routing_Version_Number!="NO")
				{
					$A_ROW=$A_Record_Type.$A_Transmission_Identifier.$A_Sender_Interchange_Address.$A_Receiver_Interchange_Address.$A_Trading_Partner_Identifier.$A_Carrier.$A_File_Generation_Date.$A_File_Generation_Time.$A_File_Version_Number.$A_Routing_Effective_Date.$A_Routing_Version_Number."\n";
					//echo "<br/> A_length:".strlen($A_ROW);
					fwrite($fp,$A_ROW);
					$et_line_no++;
				}
				
				//Manifest FOR RECORD B
				//Mandatory, 1 per Sender Identifier Code/Account Number
				$B_Record_Type="B"; //CHAR 1  //Total Record Length		186
				$B_Manifest_Identifier= $manifest_id; //"1000000001"; //CHAR BF 10
				$B_Manifest_Identifier=getRecord($B_Manifest_Identifier,"CHAR",10, "BF" );//CHAR BF 10
				$B_Sender_Identifier_Code=$sender_id_code; //"INXPX          "; //CHAR BF 15
				$B_Sender_Identifier_Code=getRecord($B_Sender_Identifier_Code,"CHAR",15, "BF" ,STR_PAD_RIGHT);//CHAR BF 15
				
				$B_Sender_Account_Number=$billing_account; //"21766577  "; //CHAR BF 10
				if($billing_type_str!="S") $B_Sender_Account_Number="          ";
				
				$B_Sender_Account_Number=getRecord($B_Sender_Account_Number,"CHAR",10, "BF" ,STR_PAD_RIGHT);//CHAR BF 10
				$B_Sender_Name=$sender_companyname; //CHAR BF 30
				$B_Sender_Name=getRecord($B_Sender_Name,"CHAR",30, "BF" ,STR_PAD_RIGHT);//CHAR BF 30
				$B_Sender_Address_line1=$sender_addr; //CHAR BF 30
				$B_Sender_Address_line1=getRecord($B_Sender_Address_line1,"CHAR",30, "BF" ,STR_PAD_RIGHT);//CHAR BF 30
				$B_Sender_Address_line2=$sender_addr2; //CHAR BF 30
				$B_Sender_Address_line2=getRecord($B_Sender_Address_line2,"CHAR",30, "BF" ,STR_PAD_RIGHT);//CHAR BF 30
				$B_Sender_Suburb=$sender_city; //CHAR BF 20
				$B_Sender_Suburb=getRecord($B_Sender_Suburb,"CHAR",20, "BF" ,STR_PAD_RIGHT);//CHAR BF 20
				$B_Sender_State=$sender_state; //CHAR BF 3
				$B_Sender_State=getRecord($B_Sender_State,"CHAR",3, "BF" ,STR_PAD_RIGHT);//CHAR BF 10
				$B_Sender_Postcode=$sender_postalcode; //CHAR BF 4
				$B_Sender_Postcode=getRecord($B_Sender_Postcode,"CHAR",4, "BF" ,STR_PAD_RIGHT);//CHAR BF 4
				$B_Sender_Contact_Name=$sender_contactname; //CHAR BF 20
				$B_Sender_Contact_Name=getRecord($B_Sender_Contact_Name,"CHAR",20, "BF" ,STR_PAD_RIGHT);//CHAR BF 20
				$B_Sender_Contact_Phone=$sender_phone; //CHAR BF 13
				$B_Sender_Contact_Phone=getRecord($B_Sender_Contact_Phone,"CHAR",13, "BF" ,STR_PAD_RIGHT);//CHAR BF 13
				if($B_Record_Type!="NO" && $B_Manifest_Identifier!="NO" && $B_Sender_Identifier_Code!="NO" && $B_Sender_Account_Number!="NO" && $B_Sender_Name!="NO" && $B_Sender_Address_line1!="NO" && $B_Sender_Address_line2!="NO" && $B_Sender_Suburb!="NO" && $B_Sender_State!="NO" && $B_Sender_Postcode!="NO" && $B_Sender_Contact_Name!="NO" && $B_Sender_Contact_Phone!="NO")
				{
					$B_ROW=$B_Record_Type.$B_Manifest_Identifier.$B_Sender_Identifier_Code.$B_Sender_Account_Number.$B_Sender_Name.$B_Sender_Address_line1.$B_Sender_Address_line2.$B_Sender_Suburb.$B_Sender_State.$B_Sender_Postcode.$B_Sender_Contact_Name.$B_Sender_Contact_Phone."\n";
					//echo "<br/> B_length:".strlen($B_ROW);
					fwrite($fp,$B_ROW);
					$et_line_no++;
				}
				
				//Consignment FOR RECORD C
				//Mandatory, 1 per consignment
				$C_Record_Type="C";// CHAR	1  //Total Record Length		292
				$C_Manifest_Identifier=$manifest_id; //"1000000001";// CHAR BF	10
				$C_Manifest_Identifier=getRecord($C_Manifest_Identifier,"CHAR",10, "BF" );// CHAR BF	10
				$C_Consignment_Note_Number=$airbillno; //"IXX500000001   ";//CHAR BF	15
				$C_Consignment_Note_Number=getRecord($C_Consignment_Note_Number,"CHAR",15, "BF" ,STR_PAD_RIGHT );//CHAR BF	15
				$C_Lines_This_Consignment=count($F_item_ary); // "002"; //No of Items // NUM BF	3
				$C_Lines_This_Consignment=getRecord($C_Lines_This_Consignment,"NUM",3, "ZF" );// NUM BF	3
				$C_Receiver_Identifier_Code=""; //"L.SHEWAN       ";//CHAR BF	15
				$C_Receiver_Identifier_Code=getRecord($C_Receiver_Identifier_Code,"CHAR",15, "BF" ,STR_PAD_RIGHT);//CHAR BF	15
				$C_Receiver_Account_Number= $billing_account; //"          ";// CHAR BF	10
				if($billing_type_str!="R") $C_Receiver_Account_Number="          ";

				$C_Receiver_Account_Number=getRecord($C_Receiver_Account_Number,"CHAR",10, "BF" ,STR_PAD_RIGHT);// CHAR BF	10
				$C_Receiver_Name=$receiver_companyname;// CHAR BF	30
				$C_Receiver_Name=getRecord($C_Receiver_Name,"CHAR",30, "BF" ,STR_PAD_RIGHT);// CHAR BF	30
				$C_Receiver_Address_line1=$receiver_addr;// CHAR BF	30
				$C_Receiver_Address_line1=getRecord($C_Receiver_Address_line1,"CHAR",30, "BF" ,STR_PAD_RIGHT);// CHAR BF	30
				$C_Receiver_Address_line2=$receiver_addr2;//CHAR BF	30
				$C_Receiver_Address_line2=getRecord($C_Receiver_Address_line2,"CHAR",30, "BF" ,STR_PAD_RIGHT);//CHAR BF	30
				$C_Receiver_Suburb=$receiver_city;//CHAR BF	20
				$C_Receiver_Suburb=getRecord($C_Receiver_Suburb,"CHAR",20, "BF" ,STR_PAD_RIGHT);//CHAR BF	20
				$C_Receiver_State=$receiver_state;//CHAR BF	3
				$C_Receiver_State=getRecord($C_Receiver_State,"CHAR",3, "BF" ,STR_PAD_RIGHT );//CHAR BF	3
				$C_Receiver_Postcode=$receiver_postalcode;//CHAR BF	4
				$C_Receiver_Postcode=getRecord($C_Receiver_Postcode,"CHAR",4, "BF" ,STR_PAD_RIGHT);//CHAR BF	4
				$C_Receiver_Contact_Name=$receiver_contactname;//CHAR BF	20
				$C_Receiver_Contact_Name=getRecord($C_Receiver_Contact_Name,"CHAR",20, "BF" ,STR_PAD_RIGHT);//CHAR BF	20
				$C_Receiver_Contact_Phone=$receiver_phone;//CHAR BF	13
				$C_Receiver_Contact_Phone=getRecord($C_Receiver_Contact_Phone,"CHAR",13, "BF" ,STR_PAD_RIGHT);//CHAR BF	13
				$C_Con_Note_Date= $shipmentdate; //"03252012";//CHAR 	8
				$C_Con_Note_Date=getRecord($C_Con_Note_Date,"CHAR",8, "" );//CHAR 	8
				$C_Service=$service_code; //"76  ";//CHAR BF	4
				$C_Service=getRecord($C_Service,"CHAR",4, "BF" ,STR_PAD_RIGHT );//CHAR BF	4
				$C_Dangerous_Goods_Flag="0";//CHAR	1
				if($isDangerousGood==true) $C_Dangerous_Goods_Flag="1";
				$C_Dangerous_Goods_Flag=getRecord($C_Dangerous_Goods_Flag,"CHAR",1, "" );//CHAR	1
				$C_Payer_Flag=$billing_type_str; // "T";//CHAR	1
				
				$C_Payer_Flag=getRecord($C_Payer_Flag,"CHAR",1, "" );//CHAR	1
				$C_Cancelled_Flag="0";//CHAR	1
				$C_Cancelled_Flag=getRecord($C_Cancelled_Flag,"CHAR",1, "" );//CHAR	1
				$C_Foodstuffs_Flag="0";//CHAR	1
				$C_Foodstuffs_Flag=getRecord($C_Foodstuffs_Flag,"CHAR",1, "" );//CHAR	1
				$C_Extended_Warranty_Value= "0";//"00000000";//NUM BF	8
				$C_Extended_Warranty_Value=getRecord($C_Extended_Warranty_Value,"NUM",8, "ZF" );//NUM BF	8
				$C_Extended_Warranty_Class=" ";//CHAR BF	1
				$C_Extended_Warranty_Class=getRecord($C_Extended_Warranty_Class,"CHAR",1, "BF" );//CHAR BF	1
				$C_Additional_Service1="   ";//CHAR BF	3
				$C_Additional_Service1=getRecord($C_Additional_Service1,"CHAR",3, "BF" );//CHAR BF	3
				$C_Additional_Service2="   ";//CHAR BF	3
				$C_Additional_Service2=getRecord($C_Additional_Service2,"CHAR",3, "BF" );//CHAR BF	3
				$C_Additional_Service3="   ";//CHAR BF	3
				$C_Additional_Service3=getRecord($C_Additional_Service3,"CHAR",3, "BF" );//CHAR BF	3
				$C_Additional_Service4="   ";//CHAR BF	3
				$C_Additional_Service4=getRecord($C_Additional_Service4,"CHAR",3, "BF" );//CHAR BF	3
				$C_Additional_Service5="   ";//CHAR BF	3
				$C_Additional_Service5=getRecord($C_Additional_Service5,"CHAR",3, "BF" );//CHAR BF	3
				$C_HandRate_Amount= 0.00; //15.48; //"000015.48";//NUM9.2 BF	9
				$C_HandRate_Amount=getRecord($C_HandRate_Amount,"NUM9.2",9, "ZF" );//NUM9.2 BF	9
				$C_Other_Charges= 0.00; //"000000.00";//NUM9.2 BF	9
				$C_Other_Charges=getRecord($C_Other_Charges,"NUM9.2",9, "ZF" );//NUM9.2 BF	9
				$C_Customer_Consignment_Reference="                              "; //CHAR BF	30
				$C_Customer_Consignment_Reference=getRecord($C_Customer_Consignment_Reference,"CHAR",30, "BF" ,STR_PAD_RIGHT);//CHAR BF	30
				if($C_Record_Type!="NO" && $C_Manifest_Identifier!="NO" && $C_Consignment_Note_Number!="NO" && $C_Lines_This_Consignment!="NO" && $C_Receiver_Identifier_Code!="NO" && $C_Receiver_Account_Number!="NO" && $C_Receiver_Name!="NO" && $C_Receiver_Address_line1!="NO" && $C_Receiver_Address_line2!="NO" && $C_Receiver_Suburb!="NO" && $C_Receiver_State!="NO" && $C_Receiver_Postcode!="NO" && $C_Receiver_Contact_Name!="NO" && $C_Receiver_Contact_Phone!="NO" && $C_Con_Note_Date!="NO" && $C_Service!="NO" && $C_Dangerous_Goods_Flag!="NO" && $C_Payer_Flag!="NO" && $C_Cancelled_Flag!="NO" && $C_Foodstuffs_Flag!="NO" && $C_Extended_Warranty_Value!="NO" && $C_Extended_Warranty_Class!="NO" && $C_Additional_Service1!="NO" && $C_Additional_Service2!="NO" && $C_Additional_Service3!="NO" && $C_Additional_Service4!="NO" && $C_Additional_Service5!="NO" && $C_HandRate_Amount!="NO" && $C_Other_Charges!="NO" && $C_Customer_Consignment_Reference!="NO")
				{	
					$C_ROW=$C_Record_Type.$C_Manifest_Identifier.$C_Consignment_Note_Number.$C_Lines_This_Consignment.$C_Receiver_Identifier_Code.$C_Receiver_Account_Number.$C_Receiver_Name.$C_Receiver_Address_line1.$C_Receiver_Address_line2.$C_Receiver_Suburb.$C_Receiver_State.$C_Receiver_Postcode.$C_Receiver_Contact_Name.$C_Receiver_Contact_Phone.$C_Con_Note_Date.$C_Service.$C_Dangerous_Goods_Flag.$C_Payer_Flag.$C_Cancelled_Flag.$C_Foodstuffs_Flag.$C_Extended_Warranty_Value.$C_Extended_Warranty_Class.$C_Additional_Service1.$C_Additional_Service2.$C_Additional_Service3.$C_Additional_Service4.$C_Additional_Service5.$C_HandRate_Amount.$C_Other_Charges.$C_Customer_Consignment_Reference."\n";
					//echo "<br/> C_length:".strlen($C_ROW);
					fwrite($fp,$C_ROW);
					$et_line_no++;
				}else{
					$C_ROW=$C_Record_Type.$C_Manifest_Identifier.$C_Consignment_Note_Number.$C_Lines_This_Consignment.$C_Receiver_Identifier_Code.$C_Receiver_Account_Number.$C_Receiver_Name.$C_Receiver_Address_line1.$C_Receiver_Address_line2.$C_Receiver_Suburb.$C_Receiver_State.$C_Receiver_Postcode.$C_Receiver_Contact_Name.$C_Receiver_Contact_Phone.$C_Con_Note_Date.$C_Service.$C_Dangerous_Goods_Flag.$C_Payer_Flag.$C_Cancelled_Flag.$C_Foodstuffs_Flag.$C_Extended_Warranty_Value.$C_Extended_Warranty_Class.$C_Additional_Service1.$C_Additional_Service2.$C_Additional_Service3.$C_Additional_Service4.$C_Additional_Service5.$C_HandRate_Amount.$C_Other_Charges.$C_Customer_Consignment_Reference."\n";
					//echo "<br/> XC_length:".strlen($C_ROW);
					fwrite($fp,$C_ROW);
					$et_line_no++;
				}
				
				//3.4	Third Party (Type D) Record
				//Optional, 1 per consignment where <C.Payer Flag> = “T”
				$D_Record_Type="D";//CHAR	1 //Total Record Length		168
				$D_Manifest_Identifier=$manifest_id; //"1000000001";//CHAR BF	10
				$D_Manifest_Identifier=getRecord($D_Manifest_Identifier,"CHAR",10, "BF" );//CHAR BF	10
				$D_Con_Note_Number=$airbillno; //"IXX500000001   ";//CHAR BF	15
				$D_Con_Note_Number=getRecord($D_Con_Note_Number,"CHAR",15, "BF" ,STR_PAD_RIGHT );//CHAR BF	15
				$D_Third_Party_Identifier_Code="";//CHAR BF	15
				$D_Third_Party_Identifier_Code=getRecord($D_Third_Party_Identifier_Code,"CHAR",15, "BF" ,STR_PAD_RIGHT);//CHAR BF	15
				$D_Third_Party_Account_Number=$billing_account; //"12345678  ";//CHAR BF	10
				$D_Third_Party_Account_Number=getRecord($D_Third_Party_Account_Number,"CHAR",10, "BF" ,STR_PAD_RIGHT);//CHAR BF	10
				$D_Third_Party_Name="Third Party Name";//CHAR BF	30
				$D_Third_Party_Name=getRecord($D_Third_Party_Name,"CHAR",30, "BF" ,STR_PAD_RIGHT);//CHAR BF	30
				$D_Third_Party_Address_line1="Third Party Address";//CHAR BF	30
				$D_Third_Party_Address_line1=getRecord($D_Third_Party_Address_line1,"CHAR",30, "BF",STR_PAD_RIGHT );//CHAR BF	30
				$D_Third_Party_Address_line2="";//CHAR BF	30
				$D_Third_Party_Address_line2=getRecord($D_Third_Party_Address_line2,"CHAR",30, "BF" ,STR_PAD_RIGHT);//CHAR BF	30
				$D_Third_Party_Suburb="Suburb";//CHAR BF	20
				$D_Third_Party_Suburb=getRecord($D_Third_Party_Suburb,"CHAR",20, "BF" ,STR_PAD_RIGHT);//CHAR BF	20
				$D_Third_Party_State="DSW";//CHAR BF	3
				$D_Third_Party_State=getRecord($D_Third_Party_State,"CHAR",3, "BF" ,STR_PAD_RIGHT);//CHAR BF	3
				$D_Third_Party_Postcode="1234";//CHAR BF	4
				$D_Third_Party_Postcode=getRecord($D_Third_Party_Postcode,"CHAR",4, "BF" ,STR_PAD_RIGHT);//CHAR BF	4
				if($D_Record_Type!="NO" && $D_Manifest_Identifier!="NO" && $D_Con_Note_Number!="NO" && $D_Third_Party_Identifier_Code!="NO" && $D_Third_Party_Account_Number!="NO" && $D_Third_Party_Name!="NO" && $D_Third_Party_Address_line1!="NO" && $D_Third_Party_Address_line2!="NO" && $D_Third_Party_Suburb!="NO" && $D_Third_Party_State!="NO" && $D_Third_Party_Postcode!="NO" )
				{	
					$D_ROW=$D_Record_Type.$D_Manifest_Identifier.$D_Con_Note_Number.$D_Third_Party_Identifier_Code.$D_Third_Party_Account_Number.$D_Third_Party_Name.$D_Third_Party_Address_line1.$D_Third_Party_Address_line2.$D_Third_Party_Suburb.$D_Third_Party_State.$D_Third_Party_Postcode."\n";
					//echo "<br/> D_length:".strlen($D_ROW);
					if($billing_type_str=="T"){ 
						fwrite($fp,$D_ROW);
						$et_line_no++;
					}
				}
				
				//$isSpecialInstruction=true;
				//3.5	Special Instructions and Customer Text (Type E) Record
				//Optional, up to 1 record per <E.Text Type> per consignment is allowed
				$E_Record_Type="E";//CHAR	1 //Total Record Length		107
				$E_Manifest_Identifier=$manifest_id; //"1000000001";//CHAR BF	10
				$E_Manifest_Identifier=getRecord($E_Manifest_Identifier,"CHAR",10, "BF" );//CHAR BF	10
				$E_Con_Note_Number=$airbillno; //"IXX500000001   ";//CHAR BF	15
				$E_Con_Note_Number=getRecord($E_Con_Note_Number,"CHAR",15, "BF" ,STR_PAD_RIGHT);//CHAR BF	15
				$E_Text_Type="0";//NUM	1
				/*
				“0” – print text in delivery special instructions box on consignment note
				“1”, “2”, “3” – print text as agreed with TNT, but not in the delivery or pickup special instructions boxes
				“4” – print text in pickup special instructions box on pickup labels/manifest
				*/
				$E_Text_Type=getRecord($E_Text_Type,"NUM",1, "" );//NUM	1
				//$E_Text_Line="FROM:DR.KATE DOMETT SCHOOL MEDICINE               07 4781 5608                  XX";//CHAR BF	80
				$E_Text_Line="FROM:".$B_Sender_Contact_Name.$B_Sender_Contact_Phone; 
				$E_Text_Line=getRecord($E_Text_Line,"CHAR",80, "BF" ,STR_PAD_RIGHT);//CHAR BF	80
				if($E_Record_Type!="NO" && $E_Manifest_Identifier!="NO" && $E_Con_Note_Number!="NO" && $E_Text_Type!="NO" && $E_Text_Line!="NO")
				{	
					$E_ROW=$E_Record_Type.$E_Manifest_Identifier.$E_Con_Note_Number.$E_Text_Type.$E_Text_Line."\n";
					//echo "<br/> E_length:".strlen($E_ROW);
					if($isSpecialInstruction==true){
						fwrite($fp,$E_ROW);
						$et_line_no++;
					}
				}
				
				
				
				$line_index=0;
				foreach($F_item_ary as $a){
					$l=$a['l']; $w=$a['w']; $h=$a['h'];
					$count= $a['count'];
					$weight= $a['weight'];
					$line_index++;
				
					if($dimension_unit=="IN"){ // 1 in = 2.54 cm
						$dimension_unit="CM";
						$l= $l * 2.54;
						$w= $w * 2.54;
						$h= $h * 2.54;
					}
					
					if($weight_unit=="LB"){ // 1 lb = 0.45359237 kg
						$weight_unit="KG";
						$weight = $weight * 0.45359237;
					}
					
					//3.6	Consignment Line (Type F) Record
					//Mandatory, 1 or more per consignment
					$F_Record_Type="F";//CHAR	1 ////Total Record Length	131
					$F_Manifest_Identifier=$manifest_id; //"1000000001";//CHAR BF	10
					$F_Manifest_Identifier=getRecord($F_Manifest_Identifier,"CHAR",10, "BF" );//CHAR BF	10
					$F_Con_Note_Number=$airbillno; //"IXX500000001   ";//CHAR BF	15
					$F_Con_Note_Number=getRecord($F_Con_Note_Number,"CHAR",15, "BF" ,STR_PAD_RIGHT );//CHAR BF	15
					$F_Line_Sequence=$line_index ;//NUM BF	3
					$F_Line_Sequence=getRecord($F_Line_Sequence,"NUM",3, "BF" ,STR_PAD_RIGHT );//NUM BF	3
					$F_Customer_Reference=$shipreference; //"6250.70292.0103";//CHAR BF	15
					if($line_index > 1) $F_Customer_Reference="";
					
					$F_Customer_Reference=getRecord($F_Customer_Reference,"CHAR",15, "BF" );//CHAR BF	15
					$F_Description_of_Goods=$packagename;//"SATCHEL             ";//CHAR BF	20
					$F_Description_of_Goods=getRecord($F_Description_of_Goods,"CHAR",20, "BF" ,STR_PAD_RIGHT );//CHAR BF	20
					$F_Commodity_Code="      ";//CHAR BF	6
					$F_Commodity_Code=getRecord($F_Commodity_Code,"CHAR",6, "BF" );//CHAR BF	6
					$F_Number_of_Items= $count; //"00001";//NUM BF	5
					$F_Number_of_Items=getRecord($F_Number_of_Items,"NUM",5, "ZF" );//NUM BF	5
					$F_Weight= number_format($weight ,3,'.','') ; //00001.000;//NUM9.3 BF	9
					$F_Weight=getRecord($F_Weight,"NUM9.3",9, "ZF" );//NUM9.3 BF	9
					$F_Unit_of_Measure_Weight=$weight_unit;//CHAR BF	2
					$F_Unit_of_Measure_Weight=getRecord($F_Unit_of_Measure_Weight,"CHAR",2, "BF" );//CHAR BF	2
					$F_Item_Dimension_Length=number_format($l ,2,'.',''); // 0010.00;//NUM7.3 BF	7
					$F_Item_Dimension_Length=getRecord($F_Item_Dimension_Length,"NUM7.2",7, "ZF" );//NUM7.3 BF	7
					$F_Item_Dimension_Width=number_format($w ,2,'.',''); // 0010.00;//NUM7.3 BF	7
					$F_Item_Dimension_Width=getRecord($F_Item_Dimension_Width,"NUM7.2",7, "ZF" );//NUM7.3 BF	7
					$F_Item_Dimension_Height=number_format($h ,2,'.',''); // 0010.00;//NUM7.3 BF	7
					$F_Item_Dimension_Height=getRecord($F_Item_Dimension_Height,"NUM7.2",7, "ZF" );//NUM7.3 BF	7
					$F_Unit_of_Measure_Dimension=$dimension_unit;//CHAR BF	2
					$F_Unit_of_Measure_Dimension=getRecord($F_Unit_of_Measure_Dimension,"CHAR",2, "BF" );//CHAR BF	2
					
					//formula >>  1 cm = 0.01m >> vol 1 cm3  = 0.000001
					$vol = 0.000001 * $l * $w * $h * $count;
					$F_Cubic_Volume=number_format($vol ,3,'.',''); //0.0130; // 00000.0130;//NUM10.4 BF	10
					$F_Cubic_Volume=getRecord($F_Cubic_Volume,"NUM10.4",10, "ZF" );//NUM10.4 BF	10
					$F_Unit_of_Measure_Cubic_Volume="CO";//CHAR BF	2
					$F_Unit_of_Measure_Cubic_Volume=getRecord($F_Unit_of_Measure_Cubic_Volume,"CHAR",2, "BF" );//CHAR BF	2
					$F_Article_Qty=0;//"00000";//NUM BF	5
					$F_Article_Qty=getRecord($F_Article_Qty,"NUM",5, "ZF" );//NUM BF	5
					$F_Garment_Qty=0;//"00000";//NUM BF	5
					$F_Garment_Qty=getRecord($F_Garment_Qty,"NUM",5, "ZF" );//NUM BF	5
					if($F_Record_Type!="NO" && $F_Manifest_Identifier!="NO" && $F_Con_Note_Number!="NO" && $F_Line_Sequence!="NO" && $F_Customer_Reference!="NO" && $F_Description_of_Goods!="NO" && $F_Commodity_Code!="NO" && $F_Number_of_Items!="NO" && $F_Weight!="NO" && $F_Unit_of_Measure_Weight!="NO" && $F_Item_Dimension_Length!="NO" &&  $F_Item_Dimension_Width!="NO" && $F_Item_Dimension_Height!="NO" && $F_Unit_of_Measure_Dimension!="NO" && $F_Cubic_Volume!="NO" && $F_Unit_of_Measure_Cubic_Volume!="NO" && $F_Article_Qty!="NO" && $F_Garment_Qty!="NO")
					{	
						$F_ROW=$F_Record_Type.$F_Manifest_Identifier.$F_Con_Note_Number.$F_Line_Sequence.$F_Customer_Reference.$F_Description_of_Goods.$F_Commodity_Code.$F_Number_of_Items.$F_Weight.$F_Unit_of_Measure_Weight.$F_Item_Dimension_Length. $F_Item_Dimension_Width.$F_Item_Dimension_Height.$F_Unit_of_Measure_Dimension.$F_Cubic_Volume.$F_Unit_of_Measure_Cubic_Volume.$F_Article_Qty.$F_Garment_Qty."\n";
						//echo "<br/> F_length:".strlen($F_ROW);
						fwrite($fp,$F_ROW);
						$et_line_no++;
					}
				}
					
				//	Dangerous Goods FOR G RECORD
				//Optional, data from this record is currently not imported in to the FMS database
				$G_Record_Type="G";//CHAR	1 // Total Record Length 147
				$G_Manifest_Identifier="Manifest";//CHAR BF	10
				$G_Manifest_Identifier=getRecord($G_Manifest_Identifier,"CHAR",10, "BF" );//CHAR BF	10
				$G_Con_Note_Number="Con Note";//CHAR BF	15
				$G_Con_Note_Number=getRecord($G_Con_Note_Number,"CHAR",15, "BF" );//CHAR BF	15
				$G_Line_Sequence=111;//NUM BF	3
				$G_Line_Sequence=getRecord($G_Line_Sequence,"NUM",3, "BF" );//NUM BF	3
				$G_Product_Sequence=22;//NUM BF	2
				$G_Product_Sequence=getRecord($G_Product_Sequence,"NUM",2, "BF" );//NUM BF	2
				$G_Product_Weight=333333333;//NUM9.3 BF	9
				$G_Product_Weight=getRecord($G_Product_Weight,"NUM9.3",9, "BF" );//NUM9.3 BF	9
				$G_Unit_of_Measure_Weight="KG";//CHAR BF	2
				$G_Unit_of_Measure_Weight=getRecord($G_Unit_of_Measure_Weight,"CHAR",2, "BF" );//CHAR BF	2
				$G_Haz_Material_Type="c";//CHAR BF	1
				$G_Haz_Material_Type=getRecord($G_Haz_Material_Type,"CHAR",1, "BF" );//CHAR BF	1
				$G_Haz_Material_Code="Haz";//CHAR BF	4
				$G_Haz_Material_Code=getRecord($G_Haz_Material_Code,"CHAR",4, "BF" );//CHAR BF	4
				$G_Haz_Material_Technical_Name="e80";//CHAR BF	80
				$G_Haz_Material_Technical_Name=getRecord($G_Haz_Material_Technical_Name,"CHAR",80, "BF" );//CHAR BF	80
				$G_Principle_Risk_Class="Principle";//CHAR BF	10
				$G_Principle_Risk_Class=getRecord($G_Principle_Risk_Class,"CHAR",10, "BF" );//CHAR BF	10
				$G_Packaging_Group_Code="Pack";//CHAR BF	10
				$G_Packaging_Group_Code=getRecord($G_Packaging_Group_Code,"CHAR",10, "BF" );//CHAR BF	10
				if($G_Record_Type!="NO" && $G_Manifest_Identifier!="NO" && $G_Con_Note_Number!="NO" && $G_Line_Sequence!="NO" && $G_Product_Sequence!="NO" && $G_Product_Weight!="NO" && $G_Unit_of_Measure_Weight!="NO" && $G_Haz_Material_Type!="NO" && $G_Haz_Material_Code!="NO" && $G_Haz_Material_Technical_Name!="NO" && $G_Principle_Risk_Class!="NO" && $G_Packaging_Group_Code!="NO")
				{	
					$G_ROW=$G_Record_Type.$G_Manifest_Identifier.$G_Con_Note_Number.$G_Line_Sequence.$G_Product_Sequence.$G_Product_Weight.$G_Unit_of_Measure_Weight.$G_Haz_Material_Type.$G_Haz_Material_Code.$G_Haz_Material_Technical_Name.$G_Principle_Risk_Class.$G_Packaging_Group_Code."\n";
					//echo "<br/>  G_length:".strlen($G_ROW);
					if($isDangerousGood==true){
						fwrite($fp,$G_ROW);
						$et_line_no++;
					}
				} 

				$index=0;
				foreach($item_identifier_group_ary as $a) {
					$index++;
					//3.8	Item Identifiers (Type H) Record
					//Mandatory, 1 or more per consignment
					$H_Record_Type="H";//CHAR	1 // Total Record Length 189
					$H_Manifest_Identifier=$manifest_id; //"1000000001";//CHAR BF	10
					$H_Manifest_Identifier=getRecord($H_Manifest_Identifier,"CHAR",10, "BF" );//CHAR BF	10
					$H_Con_Note_Number=$airbillno; //"IXX500000001   ";//CHAR BF	15
					//$H_Con_Note_Number="IXX500000001   ";//CHAR BF	15
					$H_Con_Note_Number=getRecord($H_Con_Note_Number,"CHAR",15, "BF" ,STR_PAD_RIGHT);//CHAR BF	15
					$H_Sequence=$index; //"001";//NUM BF	3
					$H_Sequence=getRecord($H_Sequence,"NUM",3, "ZF" );//NUM BF	3
					
					$item_id="";
					if(isset($item_identifier_ary[1+$a])) $item_id=$item_identifier_ary[1+$a];
					$H_Item_Identifier_1_9=$item_id; //"00183333500000001001";//CHAR BF	20
					$H_Item_Identifier_1_9=getRecord($H_Item_Identifier_1_9,"CHAR",20, "BF" );//CHAR BF	20
					
					$item_id="";
					if(isset($item_identifier_ary[2+$a])) $item_id=$item_identifier_ary[2+$a];
					$H_Item_Identifier_2_10=$item_id; //"00183333500000001002";//CHAR BF	20
					$H_Item_Identifier_2_10=getRecord($H_Item_Identifier_2_10,"CHAR",20, "BF" );//CHAR BF	20
					
					$item_id="";
					if(isset($item_identifier_ary[3+$a])) $item_id=$item_identifier_ary[3+$a];
					$H_Item_Identifier_3_11=$item_id; //"                    ";//CHAR BF	20
					$H_Item_Identifier_3_11=getRecord($H_Item_Identifier_3_11,"CHAR",20, "BF" );//CHAR BF	20
					
					$item_id="";
					if(isset($item_identifier_ary[4+$a])) $item_id=$item_identifier_ary[4+$a];
					$H_Item_Identifier_4_12=$item_id; //"                    ";//CHAR BF	20
					$H_Item_Identifier_4_12=getRecord($H_Item_Identifier_4_12,"CHAR",20, "BF" );//CHAR BF	20
					
					$item_id="";
					if(isset($item_identifier_ary[5+$a])) $item_id=$item_identifier_ary[5+$a];
					$H_Item_Identifier_5_13=$item_id; //"                    ";//CHAR BF	20
					$H_Item_Identifier_5_13=getRecord($H_Item_Identifier_5_13,"CHAR",20, "BF" );//CHAR BF	20
					
					$item_id="";
					if(isset($item_identifier_ary[6+$a])) $item_id=$item_identifier_ary[6+$a];
					$H_Item_Identifier_6_14=$item_id; //"                    ";//CHAR BF	20
					$H_Item_Identifier_6_14=getRecord($H_Item_Identifier_6_14,"CHAR",20, "BF" );//CHAR BF	20
					
					$item_id="";
					if(isset($item_identifier_ary[7+$a])) $item_id=$item_identifier_ary[7+$a];
					$H_Item_Identifier_7_15=$item_id; //"                    ";//CHAR BF	20
					$H_Item_Identifier_7_15=getRecord($H_Item_Identifier_7_15,"CHAR",20, "BF" );//CHAR BF	20
					
					$item_id="";
					if(isset($item_identifier_ary[8+$a])) $item_id=$item_identifier_ary[8+$a];
					$H_Item_Identifier_8_16=$item_id; //"                    ";//CHAR BF	20
					$H_Item_Identifier_8_16=getRecord($H_Item_Identifier_8_16,"CHAR",20, "BF" );//CHAR BF	20
					if($H_Record_Type!="NO" && $H_Manifest_Identifier!="NO" && $H_Con_Note_Number!="NO" && $H_Sequence!="NO" && $H_Item_Identifier_1_9!="NO" && $H_Item_Identifier_2_10!="NO" && $H_Item_Identifier_3_11!="NO" && $H_Item_Identifier_4_12!="NO" && $H_Item_Identifier_5_13!="NO" && $H_Item_Identifier_6_14!="NO" && $H_Item_Identifier_7_15!="NO" && $H_Item_Identifier_8_16!="NO")
					{	
						$H_ROW=$H_Record_Type.$H_Manifest_Identifier.$H_Con_Note_Number.$H_Sequence.$H_Item_Identifier_1_9.$H_Item_Identifier_2_10.$H_Item_Identifier_3_11.$H_Item_Identifier_4_12.$H_Item_Identifier_5_13.$H_Item_Identifier_6_14.$H_Item_Identifier_7_15.$H_Item_Identifier_8_16."\n";
						//echo "<br/>  H_length:".strlen($H_ROW);
						fwrite($fp,$H_ROW);
						$et_line_no++;
					}
				}



				// 
				//3.9	Alert Address (Type J) Record
				//Optional, 0 up to 999 per consignment
				$J_Record_Type="J";//CHAR	1 //Total Record Length		109
				$J_Manifest_Identifier=$manifest_id; //"1000000001";//CHAR BF	10
				$J_Manifest_Identifier=getRecord($J_Manifest_Identifier,"CHAR",10, "BF" );//CHAR BF	10
				$J_Con_Note_Number=$airbillno; //"IXX500000001   ";//CHAR BF	15
				$J_Con_Note_Number=getRecord($J_Con_Note_Number,"CHAR",15, "BF" ,STR_PAD_RIGHT );//CHAR BF	15
				$J_Alert_Type="OP";//CHAR BF	2
				$J_Alert_Type=getRecord($J_Alert_Type,"CHAR",2, "BF" );//CHAR BF	2
				$J_Address_Type="E";//CHAR BF	1
				$J_Address_Type=getRecord($J_Address_Type,"CHAR",1, "BF" );//CHAR BF	1
				$J_Address="tui.adams1@jcu.edu.au                                                           ";//CHAR BF	80
				$J_Address=getRecord($J_Address,"CHAR",80, "BF" );//CHAR BF	80
				if($J_Record_Type!="NO" && $J_Manifest_Identifier!="NO" && $J_Con_Note_Number!="NO" && $J_Alert_Type!="NO" && $J_Address_Type!="NO" && $J_Address!="NO")
				{	
					$J_ROW=$J_Record_Type.$J_Manifest_Identifier.$J_Con_Note_Number.$J_Alert_Type.$J_Address_Type.$J_Address."\n";
					//echo "<br/> J_length:".strlen($J_ROW);
					if($isAlertAddress==true){
						fwrite($fp,$J_ROW);
						$et_line_no++;
					}
				}

				//3.10	Special Reference (Type S) Record
				//Optional, 0 up to 999 per consignment
				$newtext = wordwrap($shipreference, 8, "\n" , true);
				$ref_ary = explode("\n",$newtext);
				foreach($ref_ary as $text){
					$text=trim($text);
					if($text=="") continue;
					//Special Reference FOR S RECORD
					//Optional, 0 up to 999 per consignment
					$S_Record_Type="S";//CHAR	1 // Total Record Length		34
					$S_Manifest_Identifier=$manifest_id; //"1000000001";//CHAR BF	10
					$S_Manifest_Identifier=getRecord($S_Manifest_Identifier,"CHAR",10, "BF" );
					$S_Con_Note_Number=$airbillno; //"IXX500000001   ";//CHAR BF	15
					$S_Con_Note_Number=getRecord($S_Con_Note_Number,"CHAR",15, "BF" ,STR_PAD_RIGHT );
					$S_Special_Reference_Text =$text; // "LEAVE     ";//CHAR BF	8
					$S_Special_Reference_Text=getRecord($S_Special_Reference_Text,"CHAR",8, "BF" ,STR_PAD_RIGHT);
					if($S_Record_Type!="NO" && $S_Manifest_Identifier!="NO" && $S_Con_Note_Number!="NO" && $S_Special_Reference_Text!="NO")
					{	
						$S_ROW=$S_Record_Type.$S_Manifest_Identifier.$S_Con_Note_Number.$S_Special_Reference_Text."\n";
						//echo "<br/> S_length:".strlen($S_ROW);
						fwrite($fp,$S_ROW);
						$et_line_no++;
					}
				}
				
				$et_line_no++;
				//Transmission Trailer FOR Z RECORD
				//Mandatory, 1 per transmission
				$Z_Record_Type="Z";//CHAR	1 // Total Record Length 26
				$Z_Transmission_Identifier= $transmision_id; //"10000000000000000001";//CHAR BF	20
				$Z_Transmission_Identifier=getRecord($Z_Transmission_Identifier,"CHAR",20,"BF" );
				$Z_Record_Check_count=$et_line_no; //67;//00067;//NUM BF	5 
				$Z_Record_Check_count=getRecord($Z_Record_Check_count,"NUM",5,"ZF" );
				if($Z_Record_Type!="NO" && $Z_Transmission_Identifier!="NO" && $Z_Record_Check_count!="NO")
				{	
					$Z_ROW=$Z_Record_Type.$Z_Transmission_Identifier.$Z_Record_Check_count."\n";
					//echo "<br/> Z_length:".strlen($Z_ROW);
					fwrite($fp,$Z_ROW);
				}

			}
		}
		
		fclose($fp); 
		
		if($trans_id!="0" && $smID!=""){
			$ret=$webshipbol->update_tnt_transmission($trans_id, $filename , $smID);
			if($ret==true) return $filename;
		}
		return "";
	}	
	
	function gettntfilename($dir , $name , $pre , $ext){
		do{    
			$seed =strtoupper( substr(md5(microtime()), 0, 2) );
			$filename = $dir.$name.$seed.$pre.$ext;
		} while (file_exists($filename));
		return $name.$seed.$pre.$ext;
	}
	function setup_tnt_default_dir($dir){
		if (is_dir(realpath($dir)) && filetype(realpath($dir)) == "dir") cleanup_dir($dir,24,60); // Cleanup tmp directory, deleteing all files older then 24 hours.		
		if (!is_dir(realpath($dir)) || filetype(realpath($dir)) != "dir") {
			echo "<br/>The specified dir is not actualy a dir";
			mkdir($dir);
		}
		if (!is_writable($dir)){
			chmod($dir, 0777);
		}
	}

	function tnt_et_file_delete($files,$local_folder="."){
		//ftp.tnt.com.au/outbox (for test)
		// FYI, Please use /savebox instead of /outbox since this is live system.
		$ftp_server= $default_date_format = trim(selectglobalsettingvalue("TNT Domestic FTP URL")); //"ftp.tnt.com.au";
		$ftp_user_name= trim(selectglobalsettingvalue("TNT Domestic FTP User Name")); //"ttintl";
		$ftp_user_pass= trim(selectglobalsettingvalue("TNT Domestic FTP Password")); //"p0werfu1";
		$server_folder = trim(selectglobalsettingvalue("TNT Domestic FTP ET File Path")); //"/savebox/"; //testing
						
		$return=0;				
		$conn_id = ftp_connect($ftp_server) or die('Couldn\'t connect');
		//echo "<br/>conn_id = ".$conn_id ; 
		if($login_result = ftp_login( $conn_id, $ftp_user_name, $ftp_user_pass )) 
		{
			//echo "<br/>login ok";
			ftp_pasv($conn_id, true);	

			//$local_folder='.';
			$fileary = explode(',',$files);
			foreach($fileary as $filename){
				$local_file= $local_folder."/" . $filename;
				$server_file= $server_folder.$filename;
				$res = ftp_size($conn_id, $server_file);
				if ($res != -1) {
					//echo "<br/>local_file = ".$local_file."<br/>server_file = ".$server_file; 
					 if(ftp_delete($conn_id,$server_file)){
						$return=1; //echo "<br/>deleted";
					}else{
						$return=0; //echo "<br/>fail delete";
					}
				}else {
					$return=-2; //echo "<br/>couldn't get the size ".$server_file;
				}
			}
		}else{
			$return=-1; //echo "<br/>fail login";
		}
		return $return;
	}
	
	function tnt_et_file_dispose($files,$local_folder="."){
		//return 0; //local testing
		
		//ftp.tnt.com.au/outbox (for test)
		// FYI, Please use /savebox instead of /outbox since this is live system.
		$ftp_server= $default_date_format = trim(selectglobalsettingvalue("TNT Domestic FTP URL")); //"ftp.tnt.com.au";
		$ftp_user_name= trim(selectglobalsettingvalue("TNT Domestic FTP User Name")); //"ttintl";
		$ftp_user_pass= trim(selectglobalsettingvalue("TNT Domestic FTP Password")); //"p0werfu1";
		$server_folder = trim(selectglobalsettingvalue("TNT Domestic FTP ET File Path")); //"/savebox/"; //testing
		$return=0;
		
		$conn_id = ftp_connect($ftp_server) or die('Couldn\'t connect');
		//echo "<br/>conn_id = ".$conn_id ; 
		if($login_result = ftp_login( $conn_id, $ftp_user_name, $ftp_user_pass )) 
		{
			//echo "<br/>login ok ".$local_folder."/" .$files;
			ftp_pasv($conn_id, true);	

			//$local_folder='.';
			$fileary = explode(',',$files);
			foreach($fileary as $filename){
				$local_file= $local_folder."/" . $filename;
				$server_file= $server_folder.$filename;
				//echo "<br/>check local_file = ".$local_file." server_file = ".$server_file; 
				if(file_exists($local_file)){
					//echo "<br/>local_file = ".$local_file." server_file = ".$server_file; 
					//$read_local_file = fopen($local_file, 'r');
					if(ftp_put($conn_id , $server_file,$local_file ,FTP_ASCII )){
						$return=1; //" ET file fail to dispose at FTP server.";
					}else{
						$return=0; //" ET file fail to dispose at FTP server.";
					}
					//fclose($read_local_file);
				}
			}
		}else{
			$return= -1; //"<br/>Login fail to TNT ftpserver.";
		}
		return $return;
	}

	function setItemgroup($ary, &$F_item_ary){
		$i=0;
		foreach($F_item_ary as $a){
			if($ary['l']==$a['l'] && $ary['w']==$a['w'] && $ary['h']==$a['h'] ){
				$count= $a['count'] +1;
				$weight = $a['weight'] + $ary['weight'] ;
				$F_item_ary[$i] = array("count"=>$count , "l"=>$a['l'], "w"=>$a['w'], "h"=>$a['h'],"weight" =>$weight );
				return 0;
			}
			$i++;
		}
		$F_item_ary[$i]=$ary;
	}
	
	function getItemIdentifier($ItemPrefix,$ConnPrefix,$ConnNumber,$count)
	{
		$ch1= ord ( substr($ConnPrefix,0,1) ) - 55 ;	
		$ch2= ord ( substr($ConnPrefix,1,1) ) - 55 ;		
		$ch3= ord ( substr($ConnPrefix,2,1) ) - 55 ;
		$ConnPrefix = $ch1.$ch2.$ch3;
		$ConnPrefix = substr($ConnPrefix,0,6);
		$count=str_pad($count, 3 , "0" ,STR_PAD_LEFT); 
		return $ItemPrefix.$ConnPrefix.$ConnNumber.$count;
	}
	
	function getRecord($text,$type,$length,$format, $padding=STR_PAD_LEFT )
	{		
		if(strlen($text)>$length){		
			$text= substr($text,0,$length);
			//return "NO1";
		}
		if($format==""){
			if(strlen($text)!=$length)
				return "NO2";
			else
				return $text;
		}
		else 
		{		
			if($type=="CHAR" && !is_string($text)){
				//return "NO3";
			}else if(substr($type,0,3)=="NUM"  && (is_string($text)) && $text!=""){			
				//return "NO4";
			}		
			if($type=="CHAR"){			
				if($format=="BF"){
					return str_pad($text,$length,' ',$padding);
				}else if($format=="ZF"){
					return str_pad($text,$length,0,$padding);
				}
			}		
			else if(substr($type,0,3)=="NUM") //{ if NUM	
			{
				$flen=trim(substr($type,3));
				if(intval(substr($type,3))!=$length && $flen!=""){
					return "NO5";
				}
				$point=end(explode('.',$flen));
				if(is_float($text) && strlen(intval($text))!=($length-2)){			
					$text=number_format($text,$point, '.', '');			
					if(strlen($text)>$length){					
						$text=substr($text,0,8);
					}
				}
				if($format=="BF"){
					$text=str_pad($text,$length,' ',$padding);				
				}else if($format=="ZF"){
					$text=str_pad($text,$length,0,$padding);
				}
				return $text;		
			}
			return "NO9";
		}	
	}
	
	function getshipmentInfobyShipID_for_ET_file($airbillno , $from='')
	{
		$query = "SELECT customer_code FROM xms_tbl_shipment where shipmentid='$airbillno';";
		//echo $query.'<br/>';exit();
		$result = mysql_query($query) or die(mysql_error().$query); 
		$row = mysql_fetch_array($result);
		$customercode = $row[0];
		$login_customer_code="";
		if($customercode=="") die("<b>No shipment found ... <br/>Please select another shipment.</b>");
					
		$code =substr($customercode , strlen($customercode )-4 );
		if($code=="0001")
			$from="franchise";
		else 
			$from="customer";

		if($from=="franchise")
			$query = "SELECT s.* , f.* , sc.confirmation_no, sc.status as pickup, sc.special_instructions FROM xms_tbl_shipment s inner join xms_tbl_franchise f on s.customer_code=f.franchise_code 
					left join xms_tbl_schedule_collection sc on sc.shipmentid=s.shipmentid
					where s.shipmentid ='$airbillno';";
		else 
			$query = "SELECT s.* , c.* , sc.confirmation_no, sc.status as pickup, sc.special_instructions FROM xms_tbl_shipment s inner join xms_tbl_customer c on s.customer_code=c.customer_code 
					left join xms_tbl_schedule_collection sc on sc.shipmentid=s.shipmentid
					where s.shipmentid ='$airbillno';";
		// echo $query;		
		$result = mysql_query($query) or die(mysql_error().$query); 
		return new readonlyresultset($result);
	}
	function get_tnt_shipment_for_et_repair($airbill_arr)
	{
		$query = "SELECT customer_code ,billing_type , billing_account,count(billing_type) as count,
				companyname, contact_name, phone, city, postal_code, state, address, address2
				 FROM xms_tbl_tnt_connote as con
				inner join xms_tbl_shipment as s on con.shipmentid=s.shipmentid
				inner join xms_tbl_address as a on a.addressid=s.senderaddressid
				where s.airbill_number in ('".$airbill_arr."')/*and s.status=0*/ group by customer_code , billing_type ,billing_account
				,companyname, contact_name, phone, city, postal_code, state, address, address2 order by connote_id,customer_code ;";	
		echo $query;
		$result = mysql_query ($query) or die("select historyd list Error: " . mysql_error() . $query);
		$totalrecords = queryFoundRows();   //will return total records count without limit 
		return new readonlyresultset ( $result,$totalrecords );
	}
	function get_tnt_shipment_detail_for_et_repair($customer_code, $billing_type , $billing_account ,$companyname, $contact_name, $phone, $city, $postal_code, $state, $address, $address2 )
	{
		$companyname=clean($companyname);
		$contact_name=clean($contact_name);
		$phone=clean($phone);
		$city=clean($city);
		$postal_code=clean($postal_code);
		$state=clean($state);
		$address=clean($address);
		$address2=clean($address2);
		$query = "SELECT con.* , customer_code , billing_type FROM xms_tbl_tnt_connote as con inner join xms_tbl_shipment as s on con.shipmentid=s.shipmentid inner join xms_tbl_address as a on a.addressid=s.senderaddressid
				where customer_code='$customer_code' and billing_type='$billing_type' and billing_account='$billing_account' 
				and companyname='$companyname' and contact_name='$contact_name' and phone='$phone' and city='$city' and postal_code='$postal_code' and state='$state' and address='$address' and address2='$address2'
				order by connote_id,s.shipmentid; ";	
		//echo $query;		
		$result = mysql_query ($query) or die("select historyd list Error: " . mysql_error() . $query);
		$totalrecords = queryFoundRows();   //will return total records count without limit 
		return new readonlyresultset ( $result, $totalrecords );
	}
	function queryFoundRows()
	{
		$qry = "SELECT FOUND_ROWS()";
		$rResultFilterTotal = mysql_query($qry) or die(mysql_error());
		$aResultFilterTotal = mysql_fetch_array($rResultFilterTotal);
		return $aResultFilterTotal[0];
	}
?>