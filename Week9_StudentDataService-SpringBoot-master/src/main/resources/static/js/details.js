let link = localStorage["student-data-link"];

$(function(){
    $.getJSON(link, displayStudentData);
});

function displayStudentData(student) {
    console.log(student);
    $("#firstName").text(student.firstName);
    $("#lastName").text(student.lastName);
    $("#programName").text(student.programName);
    $("#programYear").text(student.programYear);
    $("#programCoop").text(student.programCoop?'yes':'no');
    $("#programInternship").text(student.programInternship?'yes':'no');
}
