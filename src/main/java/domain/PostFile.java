package domain;

public class PostFile {
	private long file_num;
	private String file_name;
	private String file_origin_name;
	private String file_path;
	private boolean image;
	
	public PostFile(long file_num, String file_name, String file_origin_name, String file_path) {
		super();
		this.file_num = file_num;
		this.file_name = file_name;
		this.file_origin_name = file_origin_name;
		this.file_path = file_path;
	}

	public long getFile_num() {
		return file_num;
	}

	public void setFile_num(long file_num) {
		this.file_num = file_num;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_origin_name() {
		return file_origin_name;
	}

	public void setFile_origin_name(String file_origin_name) {
		this.file_origin_name = file_origin_name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	 public boolean isImage() {
	        return image;
    }
	 
    public void setImage(boolean image) {
        this.image = image;
    }
}
