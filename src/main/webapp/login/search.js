  $(function () {
    $('#searchInput').on('focus', function () {
      $('.search-dropdown').fadeIn(100);
    });
    $(document).on('click', function (e) {
      if (!$(e.target).closest('.search-container').length) {
        $('.search-dropdown').fadeOut(100);
      }
    });
  });