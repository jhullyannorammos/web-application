<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<meta charset="utf-8">

<title>Dashboard</title>

<!-- Bootstrap core CSS -->
<link href="sb-admin/css/bootstrap.css" rel="stylesheet">

<!-- Add custom CSS here -->
<link href="sb-admin/css/sb-admin.css" rel="stylesheet">
<link rel="stylesheet" href="sb-admin/font-awesome/css/font-awesome.min.css">

<!-- Page Specific CSS -->
<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">

</head>

<body>

	<div id="wrapper">

		<!-- Sidebar -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<!--
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <ul class="nav navbar-nav side-nav">
                        <li class="active"><a href="index.html"><i class="fa fa-dashboard"></i> Dashboard</a></li>
                        <li><a href="charts.html"><i class="fa fa-bar-chart-o"></i> Charts</a></li>
                        <li><a href="tables.html"><i class="fa fa-table"></i> Tables</a></li>
                        <li><a href="forms.html"><i class="fa fa-edit"></i> Forms</a></li>
                        <li><a href="typography.html"><i class="fa fa-font"></i> Typography</a></li>
                        <li><a href="bootstrap-elements.html"><i class="fa fa-desktop"></i> Bootstrap Elements</a></li>
                        <li><a href="bootstrap-grid.html"><i class="fa fa-wrench"></i> Bootstrap Grid</a></li>
                        <li><a href="blank-page.html"><i class="fa fa-file"></i> Blank Page</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-caret-square-o-down"></i> Dropdown <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Dropdown Item</a></li>
                                <li><a href="#">Another Item</a></li>
                                <li><a href="#">Third Item</a></li>
                                <li><a href="#">Last Item</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>

                <li><a href="application.jsp?pg=154&op=111"><i
							class="fa fa-android"></i> Atendimento</a></li>
                -->
		</nav>
		<!-- Sidebar -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html">Stocker</a>

			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->

			<div class="collapse navbar-collapse navbar-ex1-collapse">

				<ul class="nav navbar-nav side-nav">
					<li class="active"><a href="?pg=156"><i
							class="fa fa-dashboard"></i> Dashboard</a></li>
					<!-- <li><a href="application.jsp?pg=169"><i class="fa fa-users"></i> Funcionarios</a></li>-->
					<!--<li><a href="application.jsp?pg=190"><i class="fa fa-users"></i> Clientes</a></li>-->
					<!--<li><a href="application.jsp?pg=196"><i class="fa fa-user"></i> Usuarios</a></li>
					<li><a href="application.jsp?pg=230"><i	class="fa fa-table"></i> Modelos</a></li>
					<li><a href="application.jsp?pg=94"><i class="fa fa-edit"></i>	Departamentos</a></li>
					<li><a href="application.jsp?pg=98"><i	class="fa fa-table"></i> Fornecedores</a></li>
					<li><a href="application.jsp?pg=97"><i class="fa fa-edit"></i>	Mercadorias</a></li>
					<li><a href="application.jsp?pg=96"><i class="fa fa-edit"></i>	Produtos</a></li>
					<li><a href="#"><i class="fa fa-wrench"></i> Ferramentas</a></li>
					<li><a href="#"><i class="fa fa-file"></i> Relatorios</a></li>-->
				</ul>

				<ul class="nav navbar-nav navbar-right navbar-user">
					<li class="dropdown messages-dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"><i
							class="fa fa-envelope"></i> Messages <span class="badge">7</span>
							<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li class="dropdown-header">7 New Messages</li>
							<li class="message-preview"><a href="#"> <span
									class="avatar"><img src="http://placehold.it/50x50"></span>
									<span class="name">John Smith:</span> <span class="message">Hey
										there, I wanted to ask you something...</span> <span class="time"><i
										class="fa fa-clock-o"></i> 4:34 PM</span>
							</a></li>
							<li class="divider"></li>
							<li class="message-preview"><a href="#"> <span
									class="avatar"><img src="http://placehold.it/50x50"></span>
									<span class="name">John Smith:</span> <span class="message">Hey
										there, I wanted to ask you something...</span> <span class="time"><i
										class="fa fa-clock-o"></i> 4:34 PM</span>
							</a></li>
							<li class="divider"></li>
							<li class="message-preview"><a href="#"> <span
									class="avatar"><img src="http://placehold.it/50x50"></span>
									<span class="name">John Smith:</span> <span class="message">Hey
										there, I wanted to ask you something...</span> <span class="time"><i
										class="fa fa-clock-o"></i> 4:34 PM</span>
							</a></li>
							<li class="divider"></li>
							<li><a href="#">View Inbox <span class="badge">7</span></a></li>
						</ul></li>

					<li class="dropdown user-dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"><i
							class="fa fa-user"></i> Juliano Ramos <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#"><i class="fa fa-user"></i> Profile</a></li>
							<li><a href="#"><i class="fa fa-envelope"></i> Inbox <span
									class="badge">7</span></a></li>
							<li><a href="#"><i class="fa fa-gear"></i> Settings</a></li>
							<li class="divider"></li>
							<li><a href="#"><i class="fa fa-power-off"></i> Log Out</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>
	</div>
	<!-- /#wrapper -->

	<!-- JavaScript -->
	<script src="sb-admin/js/jquery-1.10.2.js"></script>
	<script src="sb-admin/js/bootstrap.js"></script>

	<!-- Page Specific Plugins -->
	<script
		src="sb-admin/http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
	<script src="sb-admin/http://cdn.oesmith.co.uk/morris-0.4.3.min.js"></script>
	<script src="sb-admin/js/morris/chart-data-morris.js"></script>
	<script src="sb-admin/js/tablesorter/jquery.tablesorter.js"></script>
	<script src="sb-admin/js/tablesorter/tables.js"></script>

</body>
</html>
