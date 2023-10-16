<html>
<body>
    <h1><%= request.getAttribute("message") %></h1>
    <p>Click <a href=<%="\'"+request.getAttribute("urlDownload").toString()+"\'"%>>here</a> to visit the file</p>
</body>
</html>