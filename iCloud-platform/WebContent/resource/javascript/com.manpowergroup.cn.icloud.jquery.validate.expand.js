// 邮政编码验证   
jQuery.validator.addMethod("postalCode", function(value, element) {
	if(value==null || value == "" || value.length == 0){
		return true;
	}
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写您的邮政编码");

//中文字两个字节
jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {
	if(value==null || value == "" || value.length == 0){
		return true;
	}
    var length = value.length;
    for(var i = 0; i < value.length; i++){
        if(value.charCodeAt(i) > 127){
            length++;
        }
    }
    
return this.optional(element) || ( length >= param[0] && length <= param[1] );   
}, $.validator.format("请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)"));




//年月验证 yyyy/MM  
jQuery.validator.addMethod("dateYM", function(value, element) {
	if(value==null || value == "" || value.length == 0){
		return true;
	}
	var ereg = /^\d{4}[\/]\d{2}$/;
	var r = value.match(ereg);
	if (r == null) {
		return false;
	}
	var args;
	if(value.indexOf("-") != -1){
		args = value.split("-");
	}else{
		args = value.split("\/");
	} 
	if(args == null || args.length != 2){
		return false;
	}
	var d = new Date(args[0], args[1] - 1, 1);
	var result = (d.getFullYear() == args[0] && (d.getMonth() + 1) == args[1]);
	return this.optional(element) || (result);
}, "请输入正确的年月,格式:yyyy/MM");

//字符验证
jQuery.validator.addMethod("stringCheck", function(value, element) { 
	if(value==null || value == "" || value.length == 0){
		return true;
	}
  return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);   
}, "只能包括中文字、英文字母、数字和下划线");

//日期验证 已经扩展到dateISO 可以直接使用dateISO
jQuery.validator.addMethod("isDate", function(value, element){
	if(value==null || value == "" || value.length == 0){
		return true;
	}
	
	var ereg = /^\d{4}[\/-]\d{1,2}[\/-]\d{1,2}$/;
	var r = value.match(ereg);
	if (r == null) {
		return false;
	}
	var args;
	if(value.indexOf("-") != -1){
		args = value.split("-");
	}else{
		args = value.split("\/");
	} 
	if(args == null || args.length != 3){
		return false;
	}
	var d = new Date(args[0], args[1] - 1, args[2]);
	var result = (d.getFullYear() == args[0] && (d.getMonth() + 1) == args[1] && d.getDate() == args[2]);
	return this.optional(element) || (result);
}, "请输入正确的日期");

//大于等于验证（只能用于字符串比较）
jQuery.validator.addMethod("greaterThan", function(value, element, param) {
	if(value==null || value == "" || value.length == 0){
		return true;
	}
	if( typeof $(param) == "undefined"  || $(param).val()==null || $(param).val() == ""){
		return true;
	}
  
return this.optional(element) || ( value >= $(param).val());   
}, $.validator.format("请确保输入的值大于或等于比较的值"));

//小于等于验证（只能用于字符串比较）
jQuery.validator.addMethod("lessThan", function(value, element, param) {
	if(value==null || value == "" || value.length == 0){
		return true;
	}
	if( typeof $(param) == "undefined"  || $(param).val()==null || $(param).val() == ""){
		return true;
	}
	return this.optional(element) || ( value <= $(param).val() );   
}, $.validator.format("请确保输入的值小于或等于比较的值"));

//大于等于验证（只能用于数字比较）
jQuery.validator.addMethod("ge", function(value, element, param) {
	
	if(value==null || value == "" || value.length == 0){
		return true;
	}
	if( typeof $(param) == "undefined"  || $(param).val()==null || $(param).val() == ""){
		return true;
	}
	var re = /^[-+]?[0-9]+(\.[0-9]+)?$/;   //判断字符串是否为数字
	if (!re.test(value) || isNaN(Number(value)) || isNaN(Number($(param).val()))) 
    { 
       return false;
    }
  
return this.optional(element) || ( Number(value) >= Number($(param).val()));   
}, $.validator.format("请确保输入的值大于或等于比较的值"));

//小于等于验证（只能用于数字比较）
jQuery.validator.addMethod("le", function(value, element, param) {
	if(value==null || value == "" || value.length == 0){
		return true;
	}
	if( typeof $(param) == "undefined"  || $(param).val()==null || $(param).val() == ""){
		return true;
	}
	
	var re = /^[-+]?[0-9]+(\.[0-9]+)?$/;   //判断字符串是否为数字
	if (!re.test(value) || isNaN(Number(value)) || isNaN(Number($(param).val()))) 
    { 
       return false;
    } 
	return this.optional(element) || ( Number(value) <= Number($(param).val()));   
}, $.validator.format("请确保输入的值小于或等于比较的值"));

//身份证号码验证
jQuery.validator.addMethod("checkID_ICCID", function(value, element, param){
	
	//如果证件类型不是身份证，不需要校验
	if(typeof $(param) == "undefined"  || $(param).val()!=1){
		return true;
	}
	if(value==null || value == "" || value.length == 0){
		return true;
	}
	/*var Errors=new Array( 
	"正确", 
	"身份证号码位数不对!", 
	"身份证号码出生日期超出范围或含有非法字符!", 
	"身份证号码校验错误!", 
	"身份证地区非法!" 
	);*/
	var Errors = new Array(true, false, false, false, false);
	var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}; 
	var ID_ICCID = value;
	var Y;
	var JYM; 
	var S,M; 
	var ID_ICCID_array = new Array(); 
	ID_ICCID_array = ID_ICCID.split(""); 
	//地区检验 
	if(area[parseInt(ID_ICCID.substr(0,2))]==null) return this.optional(element) || Errors[4]; 
	//身份号码位数及格式检验 
	switch(ID_ICCID.length){ 
	case 15: 
	if ( (parseInt(ID_ICCID.substr(6,2))+1900) % 4 == 0 || ((parseInt(ID_ICCID.substr(6,2))+1900) % 100 == 0 && (parseInt(ID_ICCID.substr(6,2))+1900) % 4 == 0 )){ 
	ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性 
	} else { 
	ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性 
	} 
	if(ereg.test(ID_ICCID)) return this.optional(element) || Errors[0]; 
	else return this.optional(element) || Errors[2]; 
	break; 
	case 18: 
	//18位身份号码检测 
	//出生日期的合法性检查  
	//闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9])) 
	//平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8])) 
	if ( parseInt(ID_ICCID.substr(6,4)) % 4 == 0 || (parseInt(ID_ICCID.substr(6,4)) % 100 == 0 && parseInt(ID_ICCID.substr(6,4))%4 == 0 )){ 
	ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式 
	} else { 
	ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式 
	} 
	if(ereg.test(ID_ICCID)){//测试出生日期的合法性 
	//计算校验位 
	S = (parseInt(ID_ICCID_array[0]) + parseInt(ID_ICCID_array[10])) * 7 
	+ (parseInt(ID_ICCID_array[1]) + parseInt(ID_ICCID_array[11])) * 9 
	+ (parseInt(ID_ICCID_array[2]) + parseInt(ID_ICCID_array[12])) * 10 
	+ (parseInt(ID_ICCID_array[3]) + parseInt(ID_ICCID_array[13])) * 5 
	+ (parseInt(ID_ICCID_array[4]) + parseInt(ID_ICCID_array[14])) * 8 
	+ (parseInt(ID_ICCID_array[5]) + parseInt(ID_ICCID_array[15])) * 4 
	+ (parseInt(ID_ICCID_array[6]) + parseInt(ID_ICCID_array[16])) * 2 
	+ parseInt(ID_ICCID_array[7]) * 1  
	+ parseInt(ID_ICCID_array[8]) * 6 
	+ parseInt(ID_ICCID_array[9]) * 3 ; 
	Y = S % 11; 
	M = "F"; 
	JYM = "10X98765432"; 
	M = JYM.substr(Y,1);//判断校验位 
	if(M == ID_ICCID_array[17]) return this.optional(element) || Errors[0]; //检测ID的校验位 
	else return this.optional(element) || Errors[3]; 
	} 
	else return this.optional(element) || Errors[2]; 
	break; 
	default: 
	return this.optional(element) || Errors[1]; 
	break; 
	} 
}, "请输入正确的身份证号码");

//银行卡校验（规则16位或者19位 纯数字）
jQuery.validator.addMethod("checkBankCard", function(value, element){

	//如果为空，不校验
	if(value==null || value == "" || value.length == 0){
		return true;
	}
	//如果不是纯数字，校验不通过
	var r = /^\d+$/;
	if(!r.test(value)) {
		return false;
	}
	//不是16位或者19位，校验不通过
	if(value.length !=16 && value.length !=19){
		return false;
	}
	return this.optional(element) || true; 
}, "请输入正确的银行账号（16位或19位数字）");

//手机号校验（规则11位纯数字）
jQuery.validator.addMethod("checkMobileCard", function(value, element){

	//如果为空，不校验
	if(value==null || value == "" || value.length == 0){
		return true;
	}
	//如果不是纯数字，校验不通过
	var r = /^\d+$/;
	if(!r.test(value)) {
		return false;
	}
	//不是16位或者19位，校验不通过
	if(value.length !=11 ){
		return false;
	}
	return this.optional(element) || true; 
}, "请输入正确的手机（11位数字）");


//身份证号码验证18位
jQuery.validator.addMethod("checkIDCard", function(value, element){
	
	var valueSub;
	
	if(value==null || value == "" || value.length == 0){
		return true;
	}else if(value.length > 15 && value.length < 18)
	{
		valueSub = value.substr(0,15);
	}else{
		valueSub = value;
	}
	if(value.length > 18)
	{
		valueSub = value.substr(0,18);
	}
	/*var Errors=new Array( 
	"正确", 
	"身份证号码位数不对!", 
	"身份证号码出生日期超出范围或含有非法字符!", 
	"身份证号码校验错误!", 
	"身份证地区非法!" 
	);*/
	var Errors = new Array(true, false, false, false, false);
	var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}; 
	var ID_ICCID = valueSub;
	var Y;
	var JYM; 
	var S,M; 
	var ID_ICCID_array = new Array(); 
	ID_ICCID_array = ID_ICCID.split(""); 
	//地区检验 
	if(area[parseInt(ID_ICCID.substr(0,2))]==null) return this.optional(element) || Errors[4]; 
	//身份号码位数及格式检验 
	switch(ID_ICCID.length){ 
//	case 15: 
//	if ( (parseInt(ID_ICCID.substr(6,2))+1900) % 4 == 0 || ((parseInt(ID_ICCID.substr(6,2))+1900) % 100 == 0 && (parseInt(ID_ICCID.substr(6,2))+1900) % 4 == 0 )){ 
//	ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性 
//	} else { 
//	ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性 
//	} 
//	if(ereg.test(ID_ICCID)) return this.optional(element) || Errors[0]; 
//	else return this.optional(element) || Errors[2]; 
//		return this.optional(element) || Errors[2]; 
//	break; 
	case 18: 
	//18位身份号码检测 
	//出生日期的合法性检查  
	//闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9])) 
	//平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8])) 
	if ( parseInt(ID_ICCID.substr(6,4)) % 4 == 0 || (parseInt(ID_ICCID.substr(6,4)) % 100 == 0 && parseInt(ID_ICCID.substr(6,4))%4 == 0 )){ 
	ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式 
	} else { 
	ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式 
	} 
	if(ereg.test(ID_ICCID)){//测试出生日期的合法性 
	//计算校验位 
	S = (parseInt(ID_ICCID_array[0]) + parseInt(ID_ICCID_array[10])) * 7 
	+ (parseInt(ID_ICCID_array[1]) + parseInt(ID_ICCID_array[11])) * 9 
	+ (parseInt(ID_ICCID_array[2]) + parseInt(ID_ICCID_array[12])) * 10 
	+ (parseInt(ID_ICCID_array[3]) + parseInt(ID_ICCID_array[13])) * 5 
	+ (parseInt(ID_ICCID_array[4]) + parseInt(ID_ICCID_array[14])) * 8 
	+ (parseInt(ID_ICCID_array[5]) + parseInt(ID_ICCID_array[15])) * 4 
	+ (parseInt(ID_ICCID_array[6]) + parseInt(ID_ICCID_array[16])) * 2 
	+ parseInt(ID_ICCID_array[7]) * 1  
	+ parseInt(ID_ICCID_array[8]) * 6 
	+ parseInt(ID_ICCID_array[9]) * 3 ; 
	Y = S % 11; 
	M = "F"; 
	JYM = "10X98765432"; 
	M = JYM.substr(Y,1);//判断校验位 
	if(M == ID_ICCID_array[17]) return this.optional(element) || Errors[0]; //检测ID的校验位 
	else return this.optional(element) || Errors[3]; 
	} 
	else return this.optional(element) || Errors[2]; 
	break; 
	default: 
	return this.optional(element) || Errors[1]; 
	break; 
	} 
}, "请输入正确的身份证号码,不支持15位");

//身份证号码验证18位
jQuery.validator.addMethod("checkID_ICCID18", function(value, element, param){
	
	var valueSub;
	//如果证件类型不是身份证，不需要校验
	if(typeof $(param) == "undefined"  || $(param).val()!=1){
		return true;
	}
	if(value==null || value == "" || value.length == 0){
		return true;
	}else if(value.length > 15 && value.length < 18)
	{
		valueSub = value.substr(0,15);
	}else{
		valueSub = value;
	}
	if(value.length > 18)
	{
		valueSub = value.substr(0,18);
	}
	/*var Errors=new Array( 
	"正确", 
	"身份证号码位数不对!", 
	"身份证号码出生日期超出范围或含有非法字符!", 
	"身份证号码校验错误!", 
	"身份证地区非法!" 
	);*/
	var Errors = new Array(true, false, false, false, false);
	var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}; 
	var ID_ICCID = valueSub;
	var Y;
	var JYM; 
	var S,M; 
	var ID_ICCID_array = new Array(); 
	ID_ICCID_array = ID_ICCID.split(""); 
	//地区检验 
	if(area[parseInt(ID_ICCID.substr(0,2))]==null) return this.optional(element) || Errors[4]; 
	//身份号码位数及格式检验 
	switch(ID_ICCID.length){ 
//	case 15: 
//	if ( (parseInt(ID_ICCID.substr(6,2))+1900) % 4 == 0 || ((parseInt(ID_ICCID.substr(6,2))+1900) % 100 == 0 && (parseInt(ID_ICCID.substr(6,2))+1900) % 4 == 0 )){ 
//	ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性 
//	} else { 
//	ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性 
//	} 
//	if(ereg.test(ID_ICCID)) return this.optional(element) || Errors[0]; 
//	else return this.optional(element) || Errors[2]; 
//		return this.optional(element) || Errors[2]; 
//	break; 
	case 18: 
	//18位身份号码检测 
	//出生日期的合法性检查  
	//闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9])) 
	//平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8])) 
	if ( parseInt(ID_ICCID.substr(6,4)) % 4 == 0 || (parseInt(ID_ICCID.substr(6,4)) % 100 == 0 && parseInt(ID_ICCID.substr(6,4))%4 == 0 )){ 
	ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式 
	} else { 
	ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式 
	} 
	if(ereg.test(ID_ICCID)){//测试出生日期的合法性 
	//计算校验位 
	S = (parseInt(ID_ICCID_array[0]) + parseInt(ID_ICCID_array[10])) * 7 
	+ (parseInt(ID_ICCID_array[1]) + parseInt(ID_ICCID_array[11])) * 9 
	+ (parseInt(ID_ICCID_array[2]) + parseInt(ID_ICCID_array[12])) * 10 
	+ (parseInt(ID_ICCID_array[3]) + parseInt(ID_ICCID_array[13])) * 5 
	+ (parseInt(ID_ICCID_array[4]) + parseInt(ID_ICCID_array[14])) * 8 
	+ (parseInt(ID_ICCID_array[5]) + parseInt(ID_ICCID_array[15])) * 4 
	+ (parseInt(ID_ICCID_array[6]) + parseInt(ID_ICCID_array[16])) * 2 
	+ parseInt(ID_ICCID_array[7]) * 1  
	+ parseInt(ID_ICCID_array[8]) * 6 
	+ parseInt(ID_ICCID_array[9]) * 3 ; 
	Y = S % 11; 
	M = "F"; 
	JYM = "10X98765432"; 
	M = JYM.substr(Y,1);//判断校验位 
	if(M == ID_ICCID_array[17]) return this.optional(element) || Errors[0]; //检测ID的校验位 
	else return this.optional(element) || Errors[3]; 
	} 
	else return this.optional(element) || Errors[2]; 
	break; 
	default: 
	return this.optional(element) || Errors[1]; 
	break; 
	} 
}, "请输入正确的身份证号码,不支持15位");

//验证密码是否一致
jQuery.validator.addMethod("checkPassword", function(value, element, param){
	if(typeof $(param) == "undefined"  || $(param).val()== "" || value != $(param).val() ){
		return false;
	}
	return this.optional(element) || true; 
}, "密码输入不一致");

//手机号码验证
jQuery.validator.addMethod("mobile", function(value, element) {
    var length = value.length;
    var mobile =  /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "手机号码格式错误");   

// 电话号码验证   
jQuery.validator.addMethod("phone", function(value, element) {
    var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
    return this.optional(element) || (tel.test(value));
}, "电话号码格式错误");

// 邮政编码验证   
jQuery.validator.addMethod("zipCode", function(value, element) {
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
}, "邮政编码格式错误");

// QQ号码验证   
jQuery.validator.addMethod("qq", function(value, element) {
    var tel = /^[1-9]\d{4,9}$/;
    return this.optional(element) || (tel.test(value));
}, "qq号码格式错误");

// IP地址验证
jQuery.validator.addMethod("ip", function(value, element) {
    var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
    return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
}, "Ip地址格式错误");

// 字母和数字的验证
jQuery.validator.addMethod("chrnum", function(value, element) {
    var chrnum = /^([a-zA-Z0-9]+)$/;
    return this.optional(element) || (chrnum.test(value));
}, "只能输入数字和字母(字符A-Z, a-z, 0-9)");

// 中文的验证
jQuery.validator.addMethod("chinese", function(value, element) {
    var chinese = /^[\u4e00-\u9fa5]+$/;
    return this.optional(element) || (chinese.test(value));
}, "只能输入中文");

// 下拉框验证
$.validator.addMethod("selectNone", function(value, element) {
    return value == "请选择";
}, "必须选择一项");

// 字节长度验证
jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {
    var length = value.length;
    for (var i = 0; i < value.length; i++) {
        if (value.charCodeAt(i) > 127) {
            length++;
        }
    }
    return this.optional(element) || (length >= param[0] && length <= param[1]);
}, $.validator.format("请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)"));
