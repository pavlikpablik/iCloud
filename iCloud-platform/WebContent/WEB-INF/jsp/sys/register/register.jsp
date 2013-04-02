<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>iCloud System Register</title>
<meta name="description" content="">
<meta name="author" content="">
<%@ taglib prefix="s" uri="/struts-tags"%>
<link
	href="resource/bootstrap2.3/css/bootstrap.css"
	rel="stylesheet">
<link
	href="resource/bootstrap2.3/css/bootstrap-responsive.css"
	rel="stylesheet">
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<script src="resource/javascript/jquery.js" type="text/javascript"></script>
<script
	src="resource/bootstrap2.3/js/bootstrap.js"
	type="text/javascript"></script>
<script src="resource/jquery-validation-1.9.0/jquery.validate.js"
	type="text/javascript"></script>
<script
	src="resource/jquery-validation-1.9.0/localization/messages_cn.js"
	type="text/javascript"></script>
<script src="resource/jquery-validation-1.9.0/lib/jquery.metadata.js"
	type="text/javascript"></script>
<!-- 扩展校验-->
<script type="text/javascript"
	src="resource/javascript/com.manpowergroup.cn.icloud.jquery.validate.expand.js"></script>
<!-- Jquery 消息提示框-->
<link href="resource/pnotify-1.2.2/jquery.pnotify.default.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resource/pnotify-1.2.2/jquery.pnotify.js"></script>

<style type="text/css">
body {
	font: 12px 'Lucida Sans Unicode', 'Trebuchet MS', Arial, Helvetica;
	margin: 0;
	background-color: #d9dee2;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#ebeef2),
		to(#d9dee2) );
	background-image: -webkit-linear-gradient(top, #ebeef2, #d9dee2);
	background-image: -moz-linear-gradient(top, #ebeef2, #d9dee2);
	background-image: -ms-linear-gradient(top, #ebeef2, #d9dee2);
	background-image: -o-linear-gradient(top, #ebeef2, #d9dee2);
	background-image: linear-gradient(top, #ebeef2, #d9dee2);
}

#register {
	background-color: #fff;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#fff),
		to(#eee) );
	background-image: -webkit-linear-gradient(top, #fff, #eee);
	background-image: -moz-linear-gradient(top, #fff, #eee);
	background-image: -ms-linear-gradient(top, #fff, #eee);
	background-image: -o-linear-gradient(top, #fff, #eee);
	background-image: linear-gradient(top, #fff, #eee);
	margin: -200px 0 0 -230px;
	padding: 30px;
	position: absolute;
	top: 30%;
	left: 30%;
	z-index: 0;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;
	-webkit-box-shadow: 0 0 2px rgba(0, 0, 0, 0.2), 0 1px 1px
		rgba(0, 0, 0, .2), 0 3px 0 #fff, 0 4px 0 rgba(0, 0, 0, .2), 0 6px 0
		#fff, 0 7px 0 rgba(0, 0, 0, .2);
	-moz-box-shadow: 0 0 2px rgba(0, 0, 0, 0.2), 1px 1px 0 rgba(0, 0, 0, .1),
		3px 3px 0 rgba(255, 255, 255, 1), 4px 4px 0 rgba(0, 0, 0, .1), 6px 6px
		0 rgba(255, 255, 255, 1), 7px 7px 0 rgba(0, 0, 0, .1);
	box-shadow: 0 0 2px rgba(0, 0, 0, 0.2), 0 1px 1px rgba(0, 0, 0, .2), 0
		3px 0 #fff, 0 4px 0 rgba(0, 0, 0, .2), 0 6px 0 #fff, 0 7px 0
		rgba(0, 0, 0, .2);
}

#register:before {
	content: '';
	position: absolute;
	z-index: -1;
	border: 1px dashed #ccc;
	top: 5px;
	bottom: 5px;
	left: 5px;
	right: 5px;
	-moz-box-shadow: 0 0 0 1px #fff;
	-webkit-box-shadow: 0 0 0 1px #fff;
	box-shadow: 0 0 0 1px #fff;
}

#header h1 a {
	height: 35px;

	width: 140px;
}

#header h2 {
	background:
		url("resource/images/register-split.jpg")
		no-repeat scroll 0 50% transparent;
	top: 40px;
	left: 160px;
	padding-left: 10px;
	position: absolute;
	padding-left: 10px;
}

h2 {
	font: 20px/1.6 "微软雅黑", "华文细黑", "黑体";
}

.steps {
	width: 990px;
	height: 35px;
	background:
		url(resource/images/register-header.png)
		no-repeat 0 0;
}

.step-1 {
	background-position: 0 0;
}

.step-2 {
	background-position: 0 -35px;
}

.step-3 {
	background-position: 0 -70px;
}

.steps li {
	display: inline;
	float: left;
	width: 33%;
	height: 35px;
	line-height: 35px;
	text-indent: -9999em;
}

/* .step-alipay { */
/* 	background-image: */
/* 		url(http://img04.taobaocdn.com/tps/i4/T1l6_cXXRlXXciC4zs-990-35.jpg); */
/* } */

#content {
	width: 990px;
	height: 400px;
	overflow: scroll;
	margin: 0px 0px 10px 0px;
}

#account {
	margin: 10px 0px 0px 0px;
}

#success {
	margin: 10px 0px 0px 0px;
	height:200px;
	text-align:center;
	vertical-align:middle; 
	line-height:200px
}

.button_class_001 {
	background-color: #ffb94b;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#fddb6f),
		to(#ffb94b) );
	background-image: -webkit-linear-gradient(top, #fddb6f, #ffb94b);
	background-image: -moz-linear-gradient(top, #fddb6f, #ffb94b);
	background-image: -ms-linear-gradient(top, #fddb6f, #ffb94b);
	background-image: -o-linear-gradient(top, #fddb6f, #ffb94b);
	background-image: linear-gradient(top, #fddb6f, #ffb94b);
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
	-moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0
		rgba(255, 255, 255, 0.3) inset;
	-webkit-box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0
		rgba(255, 255, 255, 0.3) inset;
	box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0 rgba(255, 255, 255, 0.3)
		inset;
	border-width: 1px;
	border-style: solid;
	border-color: #d69e31 #e3a037 #d5982d #e3a037;
	height: 35px;
	padding: 5px;
	width: 140px;
	cursor: pointer;
	font: bold 15px Arial, Helvetica;
	color: #8f5a0a;
}

.button_class_001:hover,.button_class_001:focus {
	background-color: #fddb6f;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#ffb94b),
		to(#fddb6f) );
	background-image: -webkit-linear-gradient(top, #ffb94b, #fddb6f);
	background-image: -moz-linear-gradient(top, #ffb94b, #fddb6f);
	background-image: -ms-linear-gradient(top, #ffb94b, #fddb6f);
	background-image: -o-linear-gradient(top, #ffb94b, #fddb6f);
	background-image: linear-gradient(top, #ffb94b, #fddb6f);
}

.button_class_001:active {
	outline: none;
	-moz-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
	-webkit-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
	box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
}

.button_class_001::-moz-focus-inner {
	border: none;
}
input.error{
	float: left;
}
select.error{
	float: left;
}
label.error {
	padding-bottom: 14px;
	padding-top: 14px;
	background-color: #FCF8E3;
	border: 1px solid #FBEED5;
	border-radius: 4px 4px 4px 4px;
	color: #C09853;
	margin-bottom: 18px;
	padding: 8px 35px 8px 14px;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
	float: left;
	height: 23px;
	margin-bottom: 0px;
	margin-left:5px;
	padding: 5px 5px 0px 5px;
}


.css-float{
	float: left;
}

.goale-font {
	font: 20px/1.6 "微软雅黑", "华文细黑", "黑体";
}
</style>

</head>
<body>
	<div id="debug"></div>
	<div id="register">
		<div id="header">
			<h1>
				<a href="http://www.manpower.com.cn/" title="万宝盛华网站">iCloud</a>
			</h1>
			<h2>账户注册</h2>
		</div>
		<div id="indicate" class="steps step-1">
			<ol>
				<li>阅读服务介绍以及服务协议</li>
				<li>填写账户信息</li>
				<li>注册成功</li>
			</ol>
		</div>
		<div id="agreement" >
			<div>
				<center>
					<h3>服务介绍以及服务协议</h3>
				</center>
			</div>
			<div id="content">
				<h5>第1条 (目的)</h5>
				<p>本服务条款（下称“本条款”）的目的在于规定商家利用北京维亚泰克网络技术有限公司（简称“公司”）提供的“商家服务”时所需遵守的“商家”和“公司”之间的全部事项。</p>

				<h5>第2条 (定义)</h5>
				<p>本条款中使用的用语定义如下，本条款中未规定的用语的定义解释遵从相关法律法规规定及不同服务类别的规定。</p>
				<ul>
					<li>“XX服务”（下简“服务”）:
						指公司所提供的服务，包括有线和无线网络及蓝牙AP、GPS等测位技术服务，支持“用户”之间的SNS分享、开放并上传特定位置信息和相互沟通媒体的服务。</li>
					<li>“XX商家服务”(下称
						“商家服务”)：指根据“商家”的要求，通过“媒体”向“用户”提供“商家”的店铺信息、广告、“优惠券”等所有服务。</li>
					<li>“店铺信息服务”：指基于“商家”提供给“公司”信息，向“用户”提供的店铺位置、联系方式、服务内容、广告、活动等信息及其他全部服务。</li>
					<li>“XX优惠服务”：
						指“商家”向进入“商家”的“用户”按一定折扣提供的产品或服务。通过“XX服务”，“用户”通过SNS分享的优惠券越多最终的折扣越低。</li>
					<li>“XX签到服务”：指“商家”通过在“商家”内设置的蓝牙AP，将有关产品及服务或优惠、活动等信息发送至到访“商家”的“用户”的智能手机上的服务。</li>
					<li>“商家”：指为利用“公司”提供的“商家服务”，按照本条款与“公司”的有关合同，使用“商家服务”的法人、个体商户或其他团体。“商家”按店铺的数量分为普通商家和连锁型商家。</li>
					<li>“用户”：指使用“公司”提供的“XX服务”的人。</li>
					<li>“媒体”：指“公司”向“用户”提供“XX服务”时所使用的手段，包括网站、手机应用等。</li>
					<li>“代理公司”：指代理“公司”确保“商家”及相关管理业务的企业。</li>
					<li>“XX商家网站”(下称“网站”）：指“商家”可使用“商家服务”的网站（http://ship.letui.com）</li>
					<li>“账号（ID）”：指为识别“商家”并使用“商家”的“商家服务”，由“商家”输入并获得“公司”承认的英文字母和数字的组合。</li>
					<li>“密码”：指“商家”自定的文字和数字的组合。为保护自身的信息,
						“商家”进入“网页”或其他有关“商家服务”的媒体时，应同时输入“账号”和“密码”。</li>
					<li>“告示”：指“商家”或“用户”在“网页”或“商家”账户的留言板上公布、制作、上传的店铺信息、文件、文章、图片、视频等内容。</li>
					<li>“管理人员”：为进行“商家服务”的综合管理和顺利运营，由公司选择的人。</li>
				</ul>

				<h5>第3条 (条款的公布与修订)</h5>
				<ul>
					<li>为使“商家”便于知悉本条款的内容，“公司”应将本条款的内容通过“网页”、“商家服务”的首页或链接页面公布，或者用其他方式向“商家”进行公示。“公司”按照前述方式向“商家”公示本条款，“商家”同意本条款并加入XX网，获得会员账号成为XX网会员时本条款即为生效。</li>
					<li>本条款可随时修订，如需修订本条款，“公司”从修订本条款之日起（下称“生效日”）30天前，将本条款修订的情况和内容等事项用以下一种以上的方式通知“用户”。
						<ol>
							<li>邮件或其他书面形式通知</li>
							<li>在“网页”的首页或链接页面上公布</li>
						</ol>
					</li>
					<li>如“公司”通过邮件或书面形式通知“商家”本条款修订的事宜及修订内容时，“公司”应将通知发送到“商家”向“公司”提供的邮箱或地址。</li>
					<li>按照本条规定，修订的条款原则上从“生效日”开始生效。</li>
					<li>就本条款的修订有异议的“商家”可以书面方式向“公司”提出异议，由“公司”和“商家”友好协商解决，如10日内仍协商不成时“商家”可终止与“公司”的相关合同，但合同终止之前“商家”已经发布或承诺的“商家服务”，“商家”仍应积极、诚实地兑现。但“商家”在收到
						“公司”的通知之后于通知中明示的“生效日”内未以书面方式向“公司”表示异议或不接收的，视为同意对本条款的修订，自“生效日”起修订的条款内容即为生效。</li>
					<li>本条通知方式及通知的效力，适用于对本条款中规定的个别或全部的条款。</li>
				</ul>
				<h5>第4条 （服务使用合同）</h5>
				<ul>
					<li>与“公司”签订有关“商家服务”使用合同（下称“合同”）的“商家”应严格履行合同，并遵守本条款。</li>
					<li>“商家”应自主设定“账号”和“密码”以及在“商家服务”中所使用的品牌、店铺名称等信息之后向“公司”确认。“商家”不可以用含有下列内容的词语申请“账号”、品牌、店铺名等。对商家申请的用语，“公司”无义务一一确认其合法性，如商家申请的词语涉及违法或侵权，由申请人自行承担责任。
						<ol>
							<li>他人的商号、商标，或者服务标志</li>
							<li>著名的第三者的姓名或他人的驰名商标</li>
							<li>违背社会秩序及公共秩序的用语</li>
							<li>根据商标法不被认可的商标或标签</li>
							<li>可使人联想到“公司”提供的业务的单词或单词组合</li>
							<li>其他法律法规禁止的用语或者有可能对第三者产生侵权的用语</li>
						</ol>
					</li>

				</ul>

				<h5>第5条 （对“商家”账户的管理）</h5>
				<ul>
					<li>“商家”自行负责“商家”账户的“账号”和“密码”的管理，“商家”应不许其他第三者使用“商家”的账户。如“商家”委托或允许其他第三者使用“账号”进行操作的，相应的责任全部由“商家”自行承担。</li>
					<li>“商家”在知道“账号”及“密码”被第三者盗用时，应立即通知“公司”，并遵从“公司”的引导采取措施。</li>
					<li>如“商家”发布的“商家服务”事项发生变更，应事先在XX网的“网页”的显眼之处发布告示，并通过“公司”的客户服务中心通知“公司”，申请“公司”协助进行变更。同时，“商家”应做好相应的措施，不得损害用户的利益。</li>
					<li>如“商家”违反本条及有关的账户管理规定，发生任何损失或责任时，“公司”概不负责。</li>
				</ul>

				<h5>第6条 （“店铺信息服务”）</h5>
				<ul>
					<li>“商家”为了使用“店铺信息服务”，应按照“网页”上“公司”规定的程序，向“公司”提供“商家”自身的店铺信息。</li>
					<li>根据前款的内容，“公司”可利用“商家”提供给“公司”的店铺信息，诚实地向“用户”提供“店铺信息服务”。</li>
					<li>如“商家”需要修改“店铺信息服务”中的店铺信息时，“商家”应书面向“公司”提出申请，“公司”按照“公司”的“商家服务”政策处理“商家”的修正要求。</li>
					<li>即便“商家”没有提出修正要求，确实需要修改相关信息时，“公司”应通过“公司”内部所规定的程序修正有关信息，同时“公司”应将修正内容通过“公司”指定的方式事后通知“商家”。</li>
					<li>如“商家”变更店铺信息或结束营业时，应提前书面向
						“公司”通知有关的事项，并采取相应的措施，不得损害“用户”的利益。“公司”应向“用户”反映有关事项，并通过“店铺信息服务”将相关的信息诚实地提供给“用户”。</li>
					<li>没有正当理由，“商家”对“公司”的“店铺信息服务”中，针对“商家”没有直接向“公司”提供的信息，不可要求删除或排除在搜索结果以外。</li>
				</ul>
				<h5>第7条 （“广告服务”）</h5>
				<ul>
					<li>与“公司”签订合同的“商家”要求按照“公司”规定的申请程序申请广告刊登时，应与“公司”签订广告合同，“公司”可在“媒体”上刊登广告。</li>
					<li>“商家”必须按照“公司”要求的样式和程序申请刊登广告。“公司”可区分普通商家和连锁型商家及“代理公司”设定不同的“广告服务”申请程序。</li>
					<li>对“商家”的“广告服务”申请，广告刊登与否的决定权在于“公司”。按照有关法律法规及“公司”的规定，“公司”可要求“商家”变更申请的广告内容。</li>
					<li>“商家”向“公司”申请“广告服务”的费用，可参照“广告服务”相关的“商家”使用指南。</li>
					<li>根据“公司”规定的“广告服务”的运营规则，可显示广告商品。“商家”申请该“广告服务”之前应详细确认相关规则。</li>
					<li>如“公司”发现广告中含有社会争议内容、违背有关“商家”行业管理规定的内容及虚假信息时，无论是否在广告刊登期间，“公司”可不经任何事先通知终止该等广告的刊登。</li>
					<li>“公司”跟据法律法规和政策的规定及“公司”的情况，可随时调整“媒体”的添加及删除、“媒体”的刊登位置、刊登位置的增加、刊登顺序及刊登位置UI等，并且无需对此进行任何公示。</li>
					<li>“公司”为提高服务品质及加强“商家”的广告效果，可在没有事先告知的前提下对全部通信量中的一部分进行试验。</li>
					<li>广告合同终止后，“商家”上传的店铺信息及广告内容有可能在“媒体”的免费信息领域或“网页”上显示。如“商家”要求删除该等信息时，“公司”应在接到书面通知后五个工作日内删除。</li>
					<li>“公司”没有义务审查有关广告内容的真实性和合法性。如所刊登的广告内容被确认为虚假或违法，或违反“公司”的服务运营原则时，“公司”有权随时取消有关广告的刊登。“商家”应对该等广告的发布自行承担责任。</li>

				</ul>
				<h5>第8条 （“XX优惠服务”）</h5>
				<ul>
					<li>与“公司”签订合同的“商家”依照“公司”规定的申请程序可以向“公司”申请在“商家”店铺中向“用户”提供可使用的“XX优惠服务”。“公司”对“商家”提出的申请，根据“公司”的规定进行审核，通过审核后可提供“XX优惠服务”。公司可区分普通商家和连锁型商家及“代理公司”设定不同的“优惠券服务”申请程序。</li>
					<li>“公司”根据“商家”的“XX优惠服务”发布要求，通过“媒体”发布，便于“用户”使用“XX优惠服务”。</li>
					<li>“商家”应在“公司”提供的格式范围内制作“XX优惠服务”的设计及文字等，“公司”按有关法律法规及“公司”的规定可要求或直接变更“商家”申请的“XX优惠服务”的内容等。如发生上述情况，“公司”应立即通知“商家”该等变更事实。</li>
					<li>“商家”必须保证兑现并履行向“用户”发布的“XX优惠服务”内容
						，不得妨碍“XX优惠服务”的正常使用。“商家”应对使用“XX优惠服务”的“用户”一视同仁，不得与其他客户区别对待。</li>
					<li>“商家”必须保证，即使与“公司”解除或终止合同，其之前已发布的“XX优惠服务”仍持续有效。</li>
					<li>“公司”对“商家”利用“公司”的“媒体”发布“XX优惠服务”内容的真实性、合法性及“XX优惠服务”使用的可能性概不负责。如该等内容存在虚假、违法或违反“公司”的服务运营原则的情形时，“公司”可随时终止有关“XX优惠服务”的发布，因此所造成的“公司”的损失，“商家”应进行赔偿。</li>
				</ul>
				<h5>第9条 （“XX签到服务”）</h5>
				<ul>
					<li>与“公司”签订合同的“商家”依照“公司”规定的申请程序可以向“公司”申请在“商家”店铺中向“用户”提供可使用的“XX签到服务”。“公司”对“商家”提出的申请，根据“公司”的规定进行审核，通过审核后可提供“XX签到服务”。“公司”可区分普通商家和连锁型商家以及“代理公司”设定不同的“XX签到服务”申请程序。</li>
					<li>“公司”根据“商家”的“XX签到服务”发布要求，通过“媒体”发布，以“用户”使用“XX签到服务”。</li>
					<li>“商家”应在“公司”提供的格式范围内制作“XX签到服务”的设计及文字等，“公司”按有关法律法规及“公司”的规定可要求或直接变更“商家”申请的“XX签到服务”的内容等。如发生上述情况，“公司”应立即通知“商家”该等变更事实。</li>
					<li>“商家”必须保证，其向“用户”发布的“XX签到服务”的内容不得妨碍“XX签到服务”的正常使用。“商家”应对使用“XX签到服务”的“用户”一视同仁，并不得与其他客户区别对待。</li>
					<li>“公司”对“商家”利用“公司”的“媒体”发布的“XX签到服务”内容的真实性、合法性及“XX签到服务”使用的可能性概不负责。如该等内容被确认为虚假、违法或违反“公司”的服务运营原则时，“公司”可随时终止有关“XX签到服务”的发布，因此所造成的“公司”的损失，“商家”应进行赔偿。</li>

				</ul>
				<h5>第10条 （变更再审核）</h5>
				<p>广告内容或“XX服务”发布后，其素材发生变更时，“商家”应及时向“公司”通报。如变更后的内容仍不符合“公司”的审核标准时，“公司”可通知终止提供的“XX服务”。终止“XX服务”的情况下，从发出通知之日的次日起五个工作日以内进行再审。因终止“XX服务”而导致的损失由“商家”承担。</p>
				<h5>第11条 （停止使用及使用限制）</h5>
				<ul>
					<li>如“商家”发生以下情况时，“公司”可停止“商家”使用“商家服务”或解除同商家的合同。
						<ol>
							<li>“公司”提供“商家服务”之后，发生本条款第4条第②款的情形时</li>
							<li>两个以上的“商家”共用一个“账号”时</li>
							<li>其他违反法律法规或本条款的情形</li>
						</ol>

					</li>
					<li>如“公司”根据前款规定终止或解除合同时，应通知“商家”。如事先通知有困难时，可事后通知。</li>
				</ul>
				<h5>第12条 （服务的变更及公告）</h5>
				<ul>
					<li>“公司”根据“公司”的合理判断可变更“商家服务”的内容、质量或技术配置。在此情况下，“公司”明示将要变更的“商家服务”的内容及提供日期，从提供日期的7天前事先公布在“商家服务”的首页上。但变更内容对“商家”明显不利时，“公司”需从适用日期开始前的30天至适用日期的前一天为止在首页上进行公告。</li>
					<li>“商家”不同意前一款的变更内容时，可书面向“公司”提出申请，双方应共同协商处理，未能达成协商一致的，暂停“商家服务”的发布。</li>

				</ul>
				<h5>第13条 （对“商家”的通知）</h5>
				<ul>
					<li>“公司”向“商家”发出通知时，如无其他特别规定，则“公司”可以选择以邮件或电话的方式向“商家”进行通知。</li>
					<li>“公司”向全体“商家”进行通知时，可以在“网页”的首页上进行公告的方式通知。但对“商家”自身的经营有重大影响的事项，需按照前款方式进行通知。</li>
					<li>“公司”对以下情况概不负责：“商家”未向
						“公司”提供畅通的联系地址及电话号码的；因“商家”的联系方式变更而未能收到通知的；因“商家”的故意或过失未能收到通知而导致的“商家”的损失。</li>

				</ul>
				<h5>第14条 （服务使用期间及服务的停止）</h5>
				<ul>
					<li>“商家服务”按照全年无休的原则提供给“商家”。但发生下列情形时，可限制或暂停部分或全部的“商家服务”。
						<ol>
							<li>“公司”对电脑、网路等信息通讯设备进行维修、检查、更换、增设及进行故障排除时</li>
							<li>需要对“网页”及服务等进行更新、升级时</li>
							<li>因通讯中断、停电或使用量骤增等原因，妨碍“商家服务”的正常使用时</li>
							<li>发生自然灾害、国家紧急情况等不可抗力事件时</li>
							<li>发生其他不能维持“商家服务”正常进行的重大事由时</li>

						</ol>
					</li>
					<li>根据前款发生“商家服务”中断时，“公司”通过“商家服务”的首页、公告板或邮件、电话等可行的方式立即将中断事实通知“商家”。但发生“公司”不可预料的情况而引起“商家服务”中断的除外。“公司”将努力尽快恢复“商家服务”，因前述原因造成的“商家服务”中断，“公司”不承担赔偿责任。</li>
				</ul>
				<h5>第15条 （服务结束）</h5>
				<p>“公司”将要结束“商家服务”时，在结束日前，以本条款第13条规定的方式向 “用户”进行通知后，即可结束“商家服务”。</p>
				<h5>第16条 （“公司”的义务）</h5>
				<ul>
					<li>“公司”维持并管理“商家服务”相关的系统和装备的最佳状态。</li>
					<li>对“商家”的保密信息承担保密义务，未经“商家”事先同意，不可泄露给其他第三者。但属于下列情形的除外：
						<ol>
							<li>根据有关法律法规的规定或国家机关的命令需进行披露时</li>
							<li>司法机关以调查刑事犯罪为目的，提出要求时</li>
							<li>其他根据有关法律规定的规定需披露时</li>
						</ol>

					</li>
					<li>关于使用“商家服务”，“商家”提出的书面意见或建议被判断为正当、合理时，“公司”应进行处理。</li>
					<li>“公司”对“商家”的行为是否符合“商家服务”提供的目的和规定，需不定期进行监督。</li>
				</ul>
				<h5>第17条 （“商家”的义务）</h5>
				<ul>
					<li>“商家”在使用“商家服务”时，需遵守有关法律法规、本条款的规定、使用介绍及有关“商家服务”通知的注意事项、“公司”通知的事项等。“商家”负责管理通过“商家服务”提供和发布的商品、广告、优惠券等。</li>
					<li>一旦“商家”发现自身“账号”被盗，或已发现第三者不正当地使用其“账号”时，应及时通知“公司”，并且应遵守“公司”的指引。</li>
					<li>“商家”账户应用于提供“商家服务”的目的，未经“公司”事先书面许可不得滥用。</li>
					<li>“商家”不得发布虚假或夸张广告，并且有义务提供“商家”的店铺及商品的正确信息。</li>
					<li>在发生“用户”投诉服务的情况下，“商家”需从投诉发生时间的24小时以内妥善处理。如因可归责于“商家”的事由，使“用户”未能享受服务的话，按照对“商家”的客户投诉处理标准采取有利于“用户”的最佳措施。“商家”的个别营业地点内发生的类似事情视为“商家”的行为。</li>
					<li>如“商家”发行的“优惠券”有特殊限制条件（不可重复使用、使用期限等）应在显著的方式明确注明，否则“商家”不得拒绝“用户”使用“优惠券”。</li>
					<li>“商家”需在店铺门口等“用户”容易识别的地方，公布其提供给“用户”的“XX服务”的事实及“公司”提供的说明书。</li>
					<li>“商家”在任何情况下，在没有经过“公司”的事先书面同意之前，均不得通过代理人(agent)、机器人(robot)、蛛网(spider)、
						脚本(script)、间谍软件(spyware)等手段或使用其他不正当的方式来进行以下各项行为，或试图进行以下行为。
						<ol>
							<li>在其他网页链接“商家服务”的行为</li>
							<li>不正当地变更或增加发布次数或点击次数的行为</li>
							<li>致使“公司”的服务器及设备发生负载的行为</li>
							<li>其他妨碍 “商家服务”的正常运营的行为</li>
						</ol>

					</li>
					<li>“商家”使用“商家服务”时不得进行下列各项行为。
						<ol>
							<li>在“网页”上不正当地宣传“商家”或第三者营业的行为</li>
							<li>盗用他人的名义加入“商家服务”的行为。在此情况下“公司”可根据有关法律法规举报“商家”，并中断当前“商家”使用的“商家服务”，并立刻解除与“商家”的合同</li>
							<li>将与“商家服务”相关的权限出售、转让、二次销售或提供给他人的行为</li>
							<li>非法使用或侵害他人的著作权等知识产权的行为</li>
							<li>妨害“公司”提供“商家服务”及第三者享受“商家服务”的行为</li>
							<li>其他违反法律法规或商业道德的行为</li>
						</ol>

					</li>
					<li>“公司”为确认“商家”的上述行为而向“商家”提出查看资料或接触访问权限时，“商家”有义务诚实地回答并积极配合。</li>
				</ul>
				<h5>第18条 （对“告示”的权利及责任）</h5>
				<ul>
					<li>在“商家服务”内发布的“告示”等内容的著作权归属该内容的上传者。</li>
					<li>经“公司”核实后发现“商家”发布的“告示”有侵害他人的知识产权或违反法律法规的内容时，“公司”可在通知“商家”后，修正、删除该等“告示”。</li>
					<li>因“商家”发布的店铺信息所引起的任何争论或纠纷由“商家”自行负责。</li>
					<li>“商家”退出“商家服务”时，公司将全部删除“商家”的账户信息及店铺“告示”。如“商家”没有事先备份该等“告示”，因此而发生的问题“公司”概不负责。但“用户”上传的关于店铺的“告示”等内容，则“商家”不可任意删除。</li>
				</ul>
				<h5>第19条 （赔偿损失等）</h5>
				<ul>
					<li>对“商家”违反有关法律法规的行为，或违反本条款的行为而导致的所有责任由“商家”自行承担。</li>
					<li>如因前项发生任何的纠纷或诉讼时，该“商家”应用自己的费用使“公司”免除责任，并且应赔偿因此给“公司”造成的所有损失。该等损失包括但不限于对“用户”的赔偿、合理的律师费和诉讼费。</li>
					<li>“公司”根据“公司”的政策和运营需要，可修正、中断、变更其免费提供的“商家服务”的一部分或全部。对此，相关法律中如无明确规定，则“公司”无需承担额外赔偿。</li>
				</ul>
				<h5>第20条 （禁止转让）</h5>
				<p>“商家”在没有“公司”的事先书面同意之前，不得将其在本条款上的地位、权利、义务的全部或一部分转让或委任第三者行使，也不得以提供担保的方式进行处分。</p>
				<h5>第21条 （责任限制等）</h5>
				<ul>
					<li>非因“公司”故意或过失引起的系统障碍、系统停止，或因自然灾害等不可抗力，导致“商家服务”的中断，“公司”对此均不承担任何责任。</li>
					<li>“公司”并不保证“商家”能达到通过使用“商家服务”所期待的服务效果，而且对使用“商家服务”所发生的或可能发生的利益或损失“公司”概不负责。</li>
					<li>“公司”对于“商家”提供的申请资料及介绍内容上注明的信息的真实性、准确性，商品的真实性、合法性等概不负责。由此发生的责任由“商家”自行承担。同时，“商家”应赔偿由此给“公司”造成的损失。</li>
					<li>“公司”与“商家”之间并不构成代理关系，对“商家”的行为及其通过
						“商家服务”向“用户”提供的产品或服务“公司”概不负责。</li>
				</ul>
				<h5>第22条 （保护“用户”）</h5>
				<ul>
					<li>“商家”在使用“商家服务”的过程中，通过合法的程序取得的“用户”的信息，不得使用于收集目的以外的用途。</li>
					<li>“商家”管理“用户”的信息时，应作为善良的管理者，竭尽全力保护“用户”的利益，并需遵守电子交易有关的法律法规。</li>
					<li>“商家”不得发送违背“用户”要求或有违法性的邮件（垃圾邮件），并且不得进行违反商业道德的营业活动。</li>
				</ul>
				<h5>第23条 （其他规则）</h5>
				<ul>
					<li>本条款中未规定的事项和关于本条款的解释，遵从现时有效的有关“商家服务”的运营原则及使用指南的规定。</li>
					<li>“公司”与“商家”之间发生纠纷时，需互相协商解决或遵从商业惯例解决，通过前述方式仍未能解决纠纷时，由北京市有管辖权的人民法院进行裁决。</li>
				</ul>

			</div>
			<!-- end of cluse_text_box -->
			<div>
				<button id="agreement_ok" class="button_class_001" value="同意以上协议">同意以上协议</button>
			</div>

		</div>

		<div id="account" class="hide">
			<div class="container-fluid">
				<form class="form-horizontal" action="register_register.action"
					id="registerForm" method="post">
					<div>
						<div class="control-group">
							<label class="control-label" for="loginName">用户名*</label>
							<div class="controls">
								<input type="text" id="loginName" name="loginName"
									placeholder="用户名">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="password">密码*</label>
							<div class="controls">
								<input type="password" id="password" name="password"
									placeholder="密码">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="surePassword">确认密码*</label>
							<div class="controls">
								<input type="password" id="surePassword" name="surePassword"
									placeholder="确认密码">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="trueName">真实姓名*</label>
							<div class="controls">
								<input type="text" id="trueName" name="trueName"
									placeholder="真实姓名">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="idCard">身份证号码&nbsp;&nbsp;</label>
							<div class="controls">
								<input type="hidden" id="idCardType" value="1"> <input
									type="text" id="idCard" name="idCard" placeholder="身份证号码">
							</div>
						</div>
						<!--      <div class="control-group">-->
						<!--      <label class="control-label" for="company">公司名称</label>-->
						<!--      <div class="controls">-->
						<!--      <input type="text" id="company" name="company"-->
						<!-- 	  placeholder="公司名称">-->
						<!--      </div>-->
						<!--      </div>-->
						
						 
		                
		                <div class="control-group">
			              <label class="control-label">公司名称*</label>
			              <div class="controls">
			                 <s:select class="errorTar" list="vendorList" id="company" name="company" listKey="id" listValue="name" headerKey="0" headerValue="请选择"/> 
		                  </div>
		                </div> 
		                
		                
		                
		                
						<!-- 				    <div class="control-group"> -->
						<!-- 				    <label class="control-label" for="sex">性别</label> -->
						<!-- 				    <div class="controls"> -->
						<!-- 				    <select > -->
						<!-- 				    <option value ="0" checke name="sex" id="sex">请选择</option> -->
						<!-- 				    <option value ="1">男</option> -->
						<!-- 				    <option value ="2">女</option> -->
						<!-- 				    </select> -->
						<!-- 				    </div> -->
						<!-- 				  </div> -->
						<div class="control-group">
							<label class="control-label" for="mobile">手机&nbsp;&nbsp;</label>
							<div class="controls">
								<input type="text" id="mobile" name="mobile" placeholder="手机">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="phone">电话&nbsp;&nbsp;</label>
							<div class="controls">
								<input type="text" id="phone" name="phone" placeholder="电话">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="email">邮箱&nbsp;&nbsp;</label>
							<div class="controls">
								<input type="text" id="email" name="email" placeholder="邮箱">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"  for="checkCode">验证码&nbsp;&nbsp;</label>
							<div class="controls">
								<input type="text" class="input-small css-float" id="checkCode" name="checkCode" placeholder="验证码">
							    <img src="kaptcha.jpg" id="kaptchaImg" class="css-float"/>
								<a href="javascript:void(0);" onclick="refreshCode();" class="css-float" >看不清，换一张</a>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
<!-- 								<input class="button_class_001"  type="button" id="registerBtn" style="width:100px" value="注册"> -->
								<button class="button_class_001"  id="backBtn" >上一步</button>								
								<button class="button_class_001"  id="registerBtn" >注册</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div id="success" class="hide" >
		<a href="logon_logonOut.action" class="goale-font" >恭喜<span id="userName"></span>注册成功。<span id="totalSecond">3</span>秒数后跳转登陆页面。</a>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
//读秒
		

	    var second = $('#totalSecond').text();

	    function redirect()
	    {
	        if (second ==0)
	        {
	            location.href = 'logon_logonOut.action';
	        } else
	        {
	        	second = second-1;
	        	$('#totalSecond').text(second);
	        }
	    }
	    
	$(function() {
		
		//同意以上条款
		$("#agreement_ok").click(function() {
			$("#agreement").hide();
			$("#success").hide();
			$("#account").show();
			$("#indicate").attr("class", "steps step-2");
			// 		$("#indicate").changeClass("steps step-2");

		});
		//不同意以上条款
		$("#agreement_no").click(function() {

		});
		//同意以上条款
		$("#backBtn").click(function() {
			$("#agreement").show();
			$("#success").hide();
			$("#account").hide();
			$("#indicate").attr("class", "steps step-1");
			// 		$("#indicate").changeClass("steps step-2");

		});
// 		提交
		$("#registerBtn").click(function() {
			var _form = $("#registerForm");
			if(!_form.valid()){
				return false;
			}
			var vendorName=$("#company").find('option:selected').text();
			var url = _form.attr("action");
			var data = _form.serialize()+"&vendorName="+vendorName;
			$.ajax({
				'url' : url,
				'data' : data,
				'cache' : false,
				'type' : 'POST',
				'dataType' : 'json',
				async : false,
				'success' : function(json) {
					if (!json.result) {
						failNotify(json, $("#debug"));
						return;
					} else {
						//successNotify(json);
						$("#agreement").hide();
						$("#account").hide();
						$("#success").show();
						$("#userName").text($("#loginName").val());
						
						$("#indicate").attr("class", "steps step-3");
						
						 setInterval("redirect()", 1000);
					}
				}
			});
		});

		//帐号重复检查
		jQuery.validator.addMethod("checkLoginName", function(value, element) {
			var url = "register_checkLoginName.action";
			var returnv = false;
			$.ajax({
				'url' : url,
				'data' : {
					"loginName" : value
				},
				'cache' : false,
				'type' : 'POST',
				'dataType' : 'json',
				async : false,
				'success' : function(result) {
					returnv = result.result;
				}
			});
			return returnv;
		}, "该用户名已经被注册。");


		jQuery.validator.addMethod("checkVendor", function(value, element) { 
			var url = "register_checkVendor.action";
			var returnv = false;
			//var vendorName=$("#company").find('option:selected').text();
			$.ajax({
				'url' : url,
				'data' : {
					"vendorId" : value
				},
				'cache' : false,
				'type' : 'POST',
				'dataType' : 'json',
				async : false,
				'success' : function(result) {
					returnv = result.result;
				}
			});
			return returnv;
		}, "该公司已经被注册。");

		

		$("#registerForm").validate(

		{
			rules : {
				loginName : {
					required : true,
					maxlength : 20,
					checkLoginName : '#loginName'

				},
				trueName : {
					required : true,
					maxlength : 20

				},
				password : { //密码2的描述多于1项使用对象类型
					required : true, //必填，这里可以是一个匿名方法
					minlength : 6,
					maxlength : 20
				},

				surePassword : { //密码2的描述多于1项使用对象类型
					required : true, //必填，这里可以是一个匿名方法
					equalTo : '#password'

				},
				idCard : {
					checkID_ICCID18 : '#idCardType'
				},
				company : {
					min : 1,
					maxlength : 40,
					checkVendor : '#company'
				},
				checkCode : {
					required : true,
					maxlength : 4
				},
				email : "email", //电子邮箱必须合法
				phone : "phone", //电子邮箱必须合法
				mobile : "mobile" //电子邮箱必须合法
			},
			messages : {
				loginName : {
					required : " 必填项信息不能为空。"

				},
				trueName : {
					required : " 必填项信息不能为空。"

				},
				password : {
					required : " 必填项信息不能为空。",
					minlength : " 密码必须由6-20个字符组成。"
				},
				checkCode : {
					required : " 请输入验证码。"
				},
				surePassword : {
					required : " 必填项信息不能为空。",
					equalTo : " 两次输入的密码不相同。"

				},
				company : {
					min : " 请选择公司。"
				},
				email : " 邮箱格式错误。" //电子邮箱必须合法
			},
			debug : true
// 			, //如果修改为true则表单不会提交
// 			submitHandler : function() {
// 				alert(12);
// // 				var _form = $("#registerForm");
// // 				var url = _form.attr("action");
// // 				var data = _form.serialize();
// // 				$.ajax({
// // 					'url' : url,
// // 					'data' : data,
// // 					'cache' : false,
// // 					'type' : 'POST',
// // 					'dataType' : 'json',
// // 					async : false,
// // 					'success' : function(json) {
// // 						if (!json.result) {
// // 							failNotify(json, $("#debug"));
// // 							return;
// // 						} else {
// // 							//successNotify(json);
// // 							$("#agreement").hide();
// // 							$("#account").hide();
// // 							$("#success").show();
// // 							$("#indicate").attr("class", "steps step-3");
// // 						}
// // 					}
// // 				});
// 				return false;
// 			}
		});
		function successNotify(json) {
			//保存成功
			$.pnotify({
				title : '成功',
				text : json.resultText,
				type : 'success',
				styling : 'bootstrap'
			});
		}
		function failNotify(json) {
			//保存失败提示信息
			$.pnotify({
				title : '失败',
				text : json.resultText,
				type : 'error',
				styling : 'bootstrap'
			});

		}
		// 	jQuery.validator.addMethod("checkLoginName", function(value, element) {  
		// 		var url = "register_checkLoginName.action";
		// 		return $.ajax({'url':url,'data':{"loginName":value},'cache': false,'type':'POST','dataType':'json',async:false,
		// 				'success':function(result){
		// 					return result.result;
		// 				}
		// 			});
		// 		}, "该帐号已被使用。"); 

		// 		$("#registerForm").validate({meta:"validate"});
		// 		$("#registerForm").validate();

	});
	
	//刷新验证码
	function refreshCode(){
		$("#kaptchaImg").attr("src","kaptcha.jpg?a="+new Date().getTime());
	}
</script>




