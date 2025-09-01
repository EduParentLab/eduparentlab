
  $(function () {
    $('.dropdown-btn').on('click', function () {
      $('.dropdown-list').toggle();
    });

    $('.dropdown-list li').on('click', function () {
      const text = $(this).text();
      $('.selected-text').text(text);
      $('.dropdown-list').hide();
    });

    $(document).on('click', function (e) {
      if (!$(e.target).closest('.dropdown-wrap').length) {
        $('.dropdown-list').hide();
      }
    });
  });
