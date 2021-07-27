const mealAjaxUrl = "profile/barriers/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
  ajaxUrl: mealAjaxUrl,
  updateTable: function () {
    $.ajax({
      type: "GET",
      url: mealAjaxUrl + "filter",
      data: $("#filter").serialize()
    }).done(updateTableByData);
  }
};

function clearFilter() {
  $("#filter")[0].reset();
  $.get(mealAjaxUrl, updateTableByData);
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
        "data": "ip_address"
      },
      {
        "data": "name"
      },
      {
        "data": "address"
      },
      {
        "render": renderEditBtn,
        "defaultContent": "",
        "orderable": false
      },
      {
        "render": renderDeleteBtn,
        "defaultContent": "",
        "orderable": false
      }
    ],
    "order": [
      [
        0,
        "desc"
      ]
    ],
    "createdRow": function (row, data, dataIndex) {
      $(row).attr("data-mealExcess", data.state);
    }
  });

//  http://xdsoft.net/jqplugins/datetimepicker/
  var startDate = $('#startDate');
  var endDate = $('#endDate');
  startDate.datetimepicker({
    timepicker: false,
    format: 'Y-m-d',
    formatDate: 'Y-m-d',
    onShow: function (ct) {
      this.setOptions({
        maxDate: endDate.val() ? endDate.val() : false
      })
    }
  });
  endDate.datetimepicker({
    timepicker: false,
    format: 'Y-m-d',
    formatDate: 'Y-m-d',
    onShow: function (ct) {
      this.setOptions({
        minDate: startDate.val() ? startDate.val() : false
      })
    }
  });

  $('#dateTime').datetimepicker({
    format: 'Y-m-d H:i'
  });
});