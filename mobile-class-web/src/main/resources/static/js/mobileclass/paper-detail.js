/**
 * Created by LXChild on 18/04/2017.
 */
$(function () {
    timer();
});

function addQuestion() {
    var questionType = $("#question-type").val();
    switch (questionType) {
        // 打开单选题模态框
        case '0':
            $("#btn-add-question").attr('data-target', "#single-sel-model");
            break;
        case '1':
            $("#btn-add-question").attr('data-target', "#multi-sel-model");
            break;
        case '2':
            $("#btn-add-question").attr('data-target', "#right-wrong-model");
            break;
        case '3':
            $("#btn-add-question").attr('data-target', "#filling-blank-model");
            break;
        case '4':
            $("#btn-add-question").attr('data-target', "#essay-model");
            break;
        default:
            break;
    }
}

function deleteSingleSel(singleSelId) {
    event.preventDefault();//使a自带的方法失效，即无法调整到href中的URL(http://www.baidu.com)
    $.ajax({
        type: "DELETE",
        url: "/singleSels/" + singleSelId,
        success: function (result) {
            alert("删除成功");
            window.location.reload();
        },
        error: function (result) {
            alert("请求失败");
        }
    });
}

function editSingleSel(id, question, answerA, answerB, answerC, answerD, answerR, score) {
    event.preventDefault();//使a自带的方法失效，即无法调整到href中的URL(http://www.baidu.com)
    $("#title-single-sel-model").html("更改单选题");
    $("#form-single-sel-model").attr("action", "/singleSels/edit/" + id);
    $("#question-single-sel-model").val(question);
    $("#answer-a-single-sel-model").val(answerA);
    $("#answer-b-single-sel-model").val(answerB);
    $("#answer-c-single-sel-model").val(answerC);
    $("#answer-d-single-sel-model").val(answerD);
    $("#answer-r-single-sel-model").val(answerR);
    $("#score-single-sel-model").val(score);
    $("#btn-single-sel-model").text("保存");
}


function deleteMultiSel(id) {
    event.preventDefault();//使a自带的方法失效，即无法调整到href中的URL(http://www.baidu.com)
    $.ajax({
        type: "DELETE",
        url: "/multiSels/" + id,
        success: function (result) {
            alert("删除成功");
            window.location.reload();
        },
        error: function (result) {
            alert("请求失败");
        }
    });
}

function editMultiSel(id, question, answerA, answerB, answerC, answerD, answerE, answerF, answerR, score) {
    event.preventDefault();//使a自带的方法失效，即无法调整到href中的URL(http://www.baidu.com)
    $("#title-multi-sel-model").html("更改多选题");
    $("#form-multi-sel-model").attr("action", "/multiSels/edit/" + id);
    $("#question-multi-sel-model").val(question);
    $("#answer-a-multi-sel-model").val(answerA);
    $("#answer-b-multi-sel-model").val(answerB);
    $("#answer-c-multi-sel-model").val(answerC);
    $("#answer-d-multi-sel-model").val(answerD);
    $("#answer-e-multi-sel-model").val(answerE);
    $("#answer-f-multi-sel-model").val(answerF);
    $("#answer-r-multi-sel-model").val(answerR);
    $("#score-multi-sel-model").val(score);
    $("#btn-multi-sel-model").text("保存");
}

function deleteRightWrong(rightWrongId) {
    event.preventDefault();//使a自带的方法失效，即无法调整到href中的URL(http://www.baidu.com)
    $.ajax({
        type: "DELETE",
        url: "/rightWrongs/" + rightWrongId,
        success: function (result) {
            alert("删除成功");
            window.location.reload();
        },
        error: function (result) {
            alert("请求失败");
        }
    });
}

function editRightWrong(rightWrongId, question, answer, score) {
    event.preventDefault();//使a自带的方法失效，即无法调整到href中的URL(http://www.baidu.com)
    $("#title-right-wrong-model").html("更改对错题");
    $("#form-right-wrong-model").attr("action", "/rightWrongs/edit/" + rightWrongId);
    $("#question-right-wrong-model").val(question);
    $("#answer-right-wrong-model").val(answer);
    $("#score-right-wrong-model").val(score);
    $("#btn-right-wrong-model").text("保存");
}

function deleteFillingBlank(id) {
    event.preventDefault();//使a自带的方法失效，即无法调整到href中的URL(http://www.baidu.com)
    $.ajax({
        type: "DELETE",
        url: "/fillingBlanks/" + id,
        success: function (result) {
            alert("删除成功");
            window.location.reload();
        },
        error: function (result) {
            alert("请求失败");
        }
    });
}

function editFillingBlank(id, question, answer, score) {
    event.preventDefault();//使a自带的方法失效，即无法调整到href中的URL(http://www.baidu.com)
    $("#title-filling-blank-model").html("更改填空题");
    $("#form-filling-blank-model").attr("action", "/fillingBlanks/edit/" + id);
    $("#question-filling-blank-model").val(question);
    $("#answer-filling-blank-model").val(answer);
    $("#score-filling-blank-model").val(score);
    $("#btn-filling-blank-model").text("保存");
}

function deleteEssay(id) {
    event.preventDefault();//使a自带的方法失效，即无法调整到href中的URL(http://www.baidu.com)
    $.ajax({
        type: "DELETE",
        url: "/essays/" + id,
        success: function (result) {
            alert("删除成功");
            window.location.reload();
        },
        error: function (result) {
            alert("请求失败");
        }
    });
}

function editEssay(id, question, answer, score) {
    event.preventDefault();//使a自带的方法失效，即无法调整到href中的URL(http://www.baidu.com)
    $("#title-essay-model").html("更改简答题");
    $("#form-essay-model").attr("action", "/essays/edit/" + id);
    $("#question-essay-model").val(question);
    $("#answer-essay-model").val(answer);
    $("#score-essay-model").val(score);
    $("#btn-essay-model").text("保存");
}

function submitSingleSel(id, paperId, count) {
    var answer = $("div[id='single-sel-radio-group" + count + "'] input:radio:checked").val();
    if (typeof(answer) == "undefined") {
        return;
    }

    $.ajax({
        type: "POST",
        url: "/answers/singleSel",
        async: false,
        data: {questionId: id, paperId: paperId, answer: answer},
        success: function (result) {
        },
        error: function (result) {
            alert("请求提交单选题失败");
        }
    });
}

function submitMultiSel(id, paperId, count) {
    var answer = [];
    $("div[id='multi-sel-checkbox-group" + count + "'] input:checkbox:checked").each(function () {
        if (typeof($(this).val()) != "undefined") {
            answer.push($(this).val());//向数组中添加元素
        }
    });

    if (answer.length < 1) {
        return;
    }
    answer = answer.join(',');

    $.ajax({
        type: "POST",
        url: "/answers/multiSel",
        async: false,
        data: {questionId: id, paperId: paperId, answer: answer},
        success: function (result) {
        },
        error: function (result) {
            alert("请求提交多选题失败");
        }
    });
}

function submitRightWrong(id, paperId, count) {
    var answer = $("div[id='right-wrong-radio-group" + count + "'] input:radio:checked").val();
    if (typeof(answer) == "undefined") {
        return;
    }

    $.ajax({
        type: "POST",
        url: "/answers/rightWrong",
        async: false,
        data: {questionId: id, paperId: paperId, answer: answer},
        success: function (result) {
        },
        error: function (result) {
            alert("请求提交对错题失败");
        }
    });
}

function submitFillingBlank(id, paperId, count) {
    var answer = $("input[id='filling-blank-answer" + count + "']").val();
    if (typeof(answer) == "undefined" || answer == "") {
        return;
    }

    $.ajax({
        type: "POST",
        url: "/answers/fillingBlank",
        async: false,
        data: {questionId: id, paperId: paperId, answer: answer},
        success: function (result) {
        },
        error: function (result) {
            alert("请求提交填空题失败");
        }
    });
}

function submitEssay(id, paperId, count) {
    var answer = $("textarea[id='essay-answer" + count + "']").val();
    if (typeof(answer) == "undefined" || answer == "") {
        return;
    }

    $.ajax({
        type: "POST",
        url: "/answers/essay",
        async: false,
        data: {questionId: id, paperId: paperId, answer: answer},
        success: function (result) {
        },
        error: function (result) {
            alert("请求提交简答题失败");
        }
    });
}

function accomplish(singSel, multiSel, rightWrong, fillingBlank, essay, paperId) {

    for (var i = 1; i <= singSel; i++) {
        $("input[id='single-sel-btn" + i + "']").trigger("click");
    }

    for (var j = 1; j <= multiSel; j++) {
        $("input[id='multi-sel-btn" + j + "']").trigger("click");
    }

    for (var k = 1; k <= rightWrong; k++) {
        $("input[id='right-wrong-btn" + k + "']").trigger("click");
    }

    for (var l = 1; l <= fillingBlank; l++) {
        $("input[id='filling-blank-btn" + l + "']").trigger("click");
    }

    for (var m = 1; m <= essay; m++) {
        $("input[id='essay-btn" + m + "']").trigger("click");
    }

    $.ajax({
        type: "POST",
        url: "/scores",
        async: false,
        data: {paperId: paperId},
        success: function (result) {
        },
        error: function (result) {
            alert("请求计算得分失败");
        }
    });

    alert("交卷成功");
    location.href="/";
}

function showAnswer(id, show) {
    show = show != 'true';
    $.ajax({
        type: "POST",
        url: "/papers/showAnswer",
        data: {id: id, show: show},
        success: function (result) {
            window.location.reload();
        },
        error: function (result) {
            alert("请求失败");
        }
    });
}

function showPaper(id, show) {
    show = show != 'true';
    $.ajax({
        type: "POST",
        url: "/papers/showPaper",
        data: {id: id, show: show},
        success: function (result) {
            window.location.reload();
        },
        error: function (result) {
            alert("请求失败");
        }
    });
}

function timer() {

    var quizTime = $("#quizTime").text();
    var quizDuration = $("#quizDuration").text();
    var hour = quizDuration / 60;
    var minutes = quizDuration % 60;

    var accomplishTime = stringToDate(quizTime);
    accomplishTime.setHours(accomplishTime.getHours() + hour);
    accomplishTime.setMinutes(accomplishTime.getMinutes() + minutes);

    var ts = (accomplishTime) - (new Date());//计算剩余的毫秒数
    var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);//计算剩余的小时数
    var mm = parseInt(ts / 1000 / 60 % 60, 10);//计算剩余的分钟数
    var ss = parseInt(ts / 1000 % 60, 10);//计算剩余的秒数

    hh = checkTime(hh);
    mm = checkTime(mm);
    ss = checkTime(ss);
    $("#countDown").text(hh + ' : ' + mm + ' : ' + ss);
    if (hh == 0 && mm == 0 && ss == 0) {
        $("#accomplish").trigger("click");
        alert("时间到，系统已自动交卷");
        return;
    }
    this.timeout = setTimeout("timer()", 1000);
}

function stringToDate(fDate){
    var fullDate = fDate.split(' ');
    var date = fullDate[0].split('-');
    var time = fullDate[1].split(':');

    return new Date(date[0], date[1], date[2], time[0], time[1], time[2]);
}

function checkTime(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

function printPaper() {
    $("#paper").print({
        globalStyles: true,
        mediaPrint: false,
        stylesheet: null,
        noPrintSelector: ".no-print",
        iframe: true,
        append: null,
        prepend: null,
        manuallyCopyFormValues: true,
        deferred: $.Deferred()
    });
}