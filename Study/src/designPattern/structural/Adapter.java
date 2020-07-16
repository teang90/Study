package designPattern.structural;


/* 한 클래스의 인터페이스를 사용하고자 하는 다른 인터페이스로 변환할 때 주로 사용
 * -> 이를 이용해, 인터페이스 호환성이 맞지 않는경우 같이 쓸 수 없는 클래스를 연관 관계로 연결하여 사용할 수 있게해준다.
 * 
 * 장점)
 * - 관계가 없는 인터페이스 간 같이 사용 가능
 * - 프로그램 검사 용이
 * - 클래스 재활용성 증가
 * 
 */ 
public class Adapter {
	
	public static void main(String[] args) {
		Adapter a = new Adapter();
		MediaPlayer player = a.new MP3();
		player.play("file.mp3");

		player = a.new FormatAdapter(a.new MP4());
		player.play("file.mp4");
		
		player = a.new FormatAdapter(a.new MKV());
		player.play("file.mkv");
	}
	
	public interface MediaPlayer{
		void play(String fileName);
	}
	
	public interface MediaPackage{
		void playFile(String fileName);
	}
	
	class MP3 implements MediaPlayer{
		@Override
		public void play(String fileName) {
			System.out.println("MP3 file : "+fileName);
		}
	}
	
	class MP4 implements MediaPackage{
		@Override
		public void playFile(String fileName) {
			System.out.println("MP4 file : "+fileName);
		}
	}

	class MKV implements MediaPackage{
		@Override
		public void playFile(String fileName) {
			System.out.println("MKV file :"+fileName);
		}
	}
	
	class FormatAdapter implements MediaPlayer{
		private MediaPackage mediaPackage;
		
		public FormatAdapter() {}

		public FormatAdapter(MediaPackage mediaPackage) {
			this.mediaPackage = mediaPackage;
		}
		
		@Override
		public void play(String fileName) {
			System.out.println("using adapter ---> ");
			mediaPackage.playFile(fileName);
		}
	}
	
}
