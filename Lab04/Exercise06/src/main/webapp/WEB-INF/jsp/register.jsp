<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        input, select, textarea {
            box-sizing: border-box;
        }
        input[type="text"], select {
            height: 24px;
        }
        td {
            padding: 4px;
        }
    </style>
</head>
<body>
    <form method="post" action="/RegisterServlet">
        <table>
            <tr>
                <td>Your Name</td>
                <td>
                    <input name="name" type="text" style="width: 200px" placeholder="Full Name">
                </td>
            </tr>
            <tr>
                <td>Email Address</td>
                <td><input name="email" type="text" style="width: 200px" placeholder="Email"></td>
            </tr>
            <tr>
                <td>Birthday</td>
                <td><input name="birthDay" type="date" style="width: 200px"></td>
            </tr>
            <tr>
                <td>Birthtime</td>
                <td><input name="birthTime" type="time" style="width: 200px"></td>
            </tr>
            <tr>
                <td>Gender</td>
                <td>
                    <input type="radio" value="male" name="gender"> Male
                    <input type="radio" value="female" name="gender"> Female
                </td>
            </tr>
            <tr>
                <td>From</td>
                <td>
                    <select name="country" style="width: 200px">
                        <option>Select a country</option>
                        <optgroup label="Asia">
                            <option value="Vietnam">Vietnam</option>
                            <option value="Laos">Laos</option>
                            <option value="Cambodia">Cambodia</option>
                            <option value="Singapore">Singapore</option>
                        </optgroup>
                        <optgroup label="Europe">
                            <option value="France">France</option>
                            <option value="Belgium">Belgium</option>
                            <option value="Italy">Italy</option>
                            <option value="Finland">Finland</option>
                            <option value="Ireland">Ireland</option>
                        </optgroup>
                    </select>
                </td>
            </tr>
            <tr>
                <td style="vertical-align: text-top">Favorite IDE</td>
                <td>
                    <div><input type="checkbox" name="favorite_ide[]" value="Visual Studio Code"> Visual Studio Code</div>
                    <div><input type="checkbox" name="favorite_ide[]" value="Sublime Text"> Sublime Text</div>
                    <div><input type="checkbox" name="favorite_ide[]" value="Eclipse"> Eclipse</div>
                    <div><input type="checkbox" name="favorite_ide[]" value="Atom"> Atom</div>
                    <div><input type="checkbox" name="favorite_ide[]" value="Intelij Idea"> Intelij Idea</div>
                </td>
            </tr>
            <tr>
                <td>TOEIC Score</td>
                <td><input name="toeic" type="range" style="width: 200px" min="5" max="990" step="5"></td>
            </tr>
            <tr>
                <td style="vertical-align: text-top">Message</td>
                <td>
                    <textarea name="message" rows="5" style="width: 200px"></textarea>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button>Register</button>
                    <button>Again</button>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>