<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판 등록</title>
	<link rel="stylesheet" type="text/css" href="/static/bs5/css/bootstrap.min.css">
	<script type="text/javascript" src="/static/bs5/js/bootstrap.min.js"></script>
</head>
<body>
	<header></header>
	<section class="container">
		<div mt-3>
			<form action="" method="post">
				<div class="mb-3">
					<input class="form-control" id="id_title" name="title" placeholder="제목을 입력하세요.">
				</div>
				<div class="mb-3">
					<textarea class="form-control" id="id-controller" name="content" placeholder="내용을 입력하세요." rows="5"></textarea>
				</div>
				<div>
					<button class="btn btn-success" type="submit">저장</button>
				</div>
			</form>
		</div>
	</section>
	<footer></footer>
</body>
</html>