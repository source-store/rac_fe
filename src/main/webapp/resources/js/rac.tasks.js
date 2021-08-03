const taskAjaxUrl = "profile/tasks/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
  ajaxUrl: taskAjaxUrl,
  updateTable: function () {
    $.ajax({
      type: "GET",
      url: taskAjaxUrl + "filter",
      data: $("#filter").serialize()
    }).done(updateTableByData);
  }
};

function clearFilter() {
  $("#filter")[0].reset();
  $.get(taskAjaxUrl, updateTableByData);
}

// http://api.jquery.com/jQuery.ajax/#using-converters
$.ajaxSetup({
  converters: {
    "text json": function (stringData) {
      var json = JSON.parse(stringData);
      if (typeof json === 'object') {
        $(json).each(function () {
          if (this.hasOwnProperty('dateTime')) {
            this.dateTime = this.dateTime.substr(0, 16).replace('T', ' ');
          }
        });
      }
      return json;
    }
  }
});

$(function () {
  makeEditable({
    "columns": [
      {
        "data": "address"
      },
      {
        "data": "number_auto"
      },
      {
        "data": "phone"
      },
      {
        "data": "registered"
      }
    ],
    "order": [
      [
        0,
        "desc"
      ]
    ],
    "createdRow": function (row, data, dataIndex) {
      $(row).attr("data-taskExcess", data.state);
    }
  });

});