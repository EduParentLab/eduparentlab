document.addEventListener("DOMContentLoaded", () => {
  const fileInput = document.getElementById("files");
  const fileNameSpan = document.getElementById("file-name");
  const MAX_FILES = 5;

  if (!fileInput) return;

  fileInput.addEventListener("change", () => {
    let files = Array.from(fileInput.files);

    if (files.length > MAX_FILES) {
      alert(`파일은 최대 ${MAX_FILES}개까지만 업로드할 수 있습니다.`);

      const dt = new DataTransfer();
      files.slice(0, MAX_FILES).forEach(f => dt.items.add(f));
      fileInput.files = dt.files;
    }

    fileNameSpan.textContent = Array.from(fileInput.files).map(f => f.name).join(", ");
  });
});
