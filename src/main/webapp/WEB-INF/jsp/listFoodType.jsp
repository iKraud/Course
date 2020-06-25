<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Просмотр списка типов продуктов</title>
</head>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link href="../static/css/style1.css" th:href="@{/css/style1.css}" rel="stylesheet" />

<body>
    <div class="pageStyle">
        <div  th:replace="fragments/menu1.html"> </div>
        <h4>Список типов продуктов</h4>
        <table width="100%" class="table">
            <thead>
            <tr>
                <th scope="col"> ID </th>
                <th scope="col"> Название </th>
            </tr>
            </thead>
            <tbody>
            <tr th:if="${listFoodType.empty}">
                <td colspan="2"> Нет типов продуктов </td>
            </tr>
            <tr th:each="item : ${listFoodType}">
                <td scope="row"><span th:text="${item.getId()}"> </span></td>
                <td>
                    <span th:text="${item.getName()}"> </span>
                </td>
            </tr>
            </tbody>
        </table>

        <!--ul class="list-group list-group-flush" >
            <span th:each="item: ${listFoodType}">
                <li class="list-group-item" th:text="@{${item.id} + ' - ' + ${item.name}}"></li>
            </span>
        </ul-->
    </div>
</body>
</html>