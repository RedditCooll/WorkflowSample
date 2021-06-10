<!DOCTYPE html>
<html>
<head>
</head>
<style>
    p {font-family: sans-serif}
    table {
        font-family: Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        width: 100%;
    }
    td {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 5px;
    }
    th {
        background-color: azure;
        border: 1px solid #dddddd;
        text-align: left;
        padding: 5px;
    }
</style>

<body>
    <br>
    <p>Hi ${toName}, </p>
    <p>${contentText}</p>
    <div class="col-4">
        <table class = "table table-border">
            <tbody>
                <tr>
                    <th scope= "col">Attri_1</th>
                    <th scope= "col">Attri_2</th>
                </tr>
                <#list contentListMap as col>
                    <tr>
                        <td>${col["attri_1"]}</td>
                        <td>${col["attri_2"]}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
        </br>
        <a href=${contentMap["link"]}>Link</a>
    </div>
</body>
</html>