package chapter9_2;

public class Button {
    OnClickListener listener;  //인터페이스 타비 필드

    void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }

    void touch(){
        listener.onClick();
    }

    static interface OnClickListener {
        void onClick();
    }
}
