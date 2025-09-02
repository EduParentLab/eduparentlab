  $(function () {
    // 1. 검색창 클릭 시 dropdown 열기
    $('#searchInput').on('focus', function () {
      $('.search-dropdown').fadeIn(100);
    });

    // 2. 외부 클릭 시 dropdown 닫기
    $(document).on('click', function (e) {
      if (!$(e.target).closest('.search-container').length) {
        $('.search-dropdown').fadeOut(100);
      }
    });
  });