<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
  <title>User Management Application</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
  <nav class="navbar navbar-expand-md navbar-dark" style="background-color: black">
    <div>
      <a href="https://www.javaguides.net" class="navbar-brand"> Game Management App </a>
    </div>

    <ul class="navbar-nav">
      <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Games</a></li>
    </ul>
  </nav>
</header>
<br>
<div class="container col-md-5">
  <div class="card">
    <div class="card-body">
      <c:if test="${game != null}">
      <form action="update" method="post">
        </c:if>
        <c:if test="${game == null}">
        <form action="insert" method="post">
          </c:if>

          <caption>
            <h2>
              <c:if test="${game != null}">
                Edit Game
              </c:if>
              <c:if test="${game == null}">
                Add New Game
              </c:if>
            </h2>
          </caption>

          <c:if test="${game != null}">
            <input  name="id" value="<c:out value='${game.id}' />" />
          </c:if>

          <fieldset class="form-group">
            <label>Game Name</label> <input type="text" value="<c:out value='${game.name}' />" class="form-control" name="name" required="required">
          </fieldset>

          <fieldset class="form-group">
            <label>Genre</label> <input type="text" value="<c:out value='${game.genre}' />" class="form-control" name="genre">
          </fieldset>

          <fieldset class="form-group">
            <label>Platform</label> <input type="text" value="<c:out value='${game.platform}' />" class="form-control" name="platform">
          </fieldset>

          <fieldset class="form-group">
            <label>Completion Status</label> <input type="text" value="<c:out value='${game.completion_status}' />" class="form-control" name="completion_status">
          </fieldset>

          <button type="submit" class="btn btn-success">Save</button>
        </form>
    </div>
  </div>
</div>
</body>

</html>