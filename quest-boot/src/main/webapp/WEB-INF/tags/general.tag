<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="includeCss" required="false" %>
<%@ attribute name="customStyle" required="false" fragment="true" %>
<%@ attribute name="customScript" required="false" fragment="true" %>

<!DOCTYPE html>
<html>
<head>
    <base href="/" />
    <title>${ title }</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Bricolage+Grotesque:opsz,wght@12..96,200..800&display=swap" rel="stylesheet">

    <c:if test="${ not empty includeCss }">
        <link rel="stylesheet" href="${includeCss}">
    </c:if>

    <style>
        .bricolage-grotesque {
            font-family: "Bricolage Grotesque", sans-serif;
            font-optical-sizing: auto;
            font-weight: 200;
            font-style: normal;
            font-variation-settings: "wdth" 100;
        }

        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
        }

        body {
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            align-items: center;
            min-height: 100vh;
            font-family:"Bricolage Grotesque", sans-serif;
            background-color: #ffffff;
        }

        header.site-header {
            padding: 10px 20px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            gap: 16px;
            width: 100%;
        }

        nav.menuItems {
            background: #ffffff;
            padding: 16px 24px;
            border-radius: 16px;
            box-shadow:  20px 20px 60px #e9e3e3,
                    -20px -20px 60px #ffffff;

        }

        nav.menuItems ul {
            list-style: none;
            display: flex;
            gap: 40px;
        }

        nav.menuItems li a {
            text-decoration: none;
            color: #1C1A1A;
            font-size: 18px;
            font-weight: 500;
            text-transform: uppercase;
            position: relative;
            transition: all 0.5s ease-in-out;
            font-family: "Bricolage Grotesque", sans-serif;
        }

        nav.menuItems li a:hover {
            text-decoration: none;
            color: #FF4742;
            font-size: 18px;
            font-weight: bolder;
            text-transform: uppercase;
            position: relative;
            transition: all 0.2s ease-in-out;
            font-family: "Bricolage Grotesque", sans-serif;
        }

        nav.menuItems li a:focus {
            text-decoration: none;
            color: #ffffff;
            font-size: 18px;
            font-weight: bolder;
            text-transform: uppercase;
            position: relative;
            transition: all 0.2s ease-in-out;
            font-family: "Bricolage Grotesque", sans-serif;
                background-color: #FF4742;
            border-radius: 24px;
            padding: 4px 8px;
        }

        .actions {
            display: flex;
            gap: 12px;
        }

        .btn {
            background: #FF4742;
            border: 1px solid #FF4742;
            border-radius: 16px;
            box-shadow: rgba(0, 0, 0, 0.1) 1px 2px 4px;
            color: #FFFFFF;
            cursor: pointer;
            font-family:  "Bricolage Grotesque", sans-serif;
            font-size: 16px;
            font-weight: 800;

            padding: 16px 16px;
            text-align: center;
            text-transform: none;

            text-decoration: none;
        }

        .btn:hover {
            background-image: url("https://images.mariouniversalis.fr/cards/illus/334.jpg");
            background-size: 70% ;
            background-position: center;
            color: #1C1A1A;
            border :1px solid  #1C1A1A;
        }

        .btn:active {
            opacity: 0.5;
        }

        <jsp:invoke fragment="customStyle" />
    </style>

	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>

<body id="fond">
    <%@ include file="/header.jsp" %>

    <h1>${ title }</h1>

    <jsp:doBody />

    <%@ include file="/footer.jsp" %>

    <c:if test="${ not empty customScript }">
        <jsp:invoke fragment="customScript" />
    </c:if>
</body>
</html>