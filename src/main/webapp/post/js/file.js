document.addEventListener("DOMContentLoaded", function () {
  const fileInput = document.getElementById("files");
  const fileNameSpan = document.getElementById("file-name");

  if (fileInput) {
    fileInput.addEventListener("change", function () {
      if (this.files.length > 0) {  
        const names = Array.from(this.files).map(f => f.name).join(", ");
        fileNameSpan.textContent = names;
      } else {
        fileNameSpan.textContent = "";
      }
    });
  }
});
