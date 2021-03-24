$(function(){
    $.getJSON("api/students", displayStudentTable);
});

function displayStudentTable(data) {
    console.log(data);
    let students = data["_embedded"].students;
    if(students.length > 0){
        $("#no_data_message").hide();
        let tbody$ = $("#data_table tbody");
        tbody$.html("");
        students.forEach(function(student, index){
            let link = student["_links"].self.href;
            tbody$.append(
                `<tr>
                    <th scope="row" class="align-middle">${index + 1}</th>
                    <td class="align-middle">${student.firstName}</td>
                    <td class="align-middle">${student.lastName}</td>
                    <td>
                        <ul class="nav justify-content-center">
                            <li class="nav-item">
                                <a class="nav-link" href="Details.html" data-link="${link}">
                                    <i class="material-icons">info</i>&nbsp;Info</a>
                            </li>
                        </ul>
                    </td>
                </tr>`);
        });
        $("#data_table a").click(function(){
            localStorage["student-data-link"] = $(this).data("link");
        });
    }else{
        $("#data_table").hide();
    }

}
