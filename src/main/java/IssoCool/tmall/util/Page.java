package IssoCool.tmall.util;

public class Page {

    int start;//起始位置
    int count;//每页总数
    int total;//总数
    String param;
    private static final int defaultCount = 5;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Page(){
        count=defaultCount;
    }

    public Page(int start,int count){
        this();
        this.start=start;
        this.count=count;
    }

    //判断最后一页数值
    public int getLast(){
       int last;
       if(0 == total%count)
           last = total-count;
       else
           last = total - total%count;

       last = last<0?0:last;
       return last;
    }

    //总共有多少页
    public int getTotalPage(){
        int totalPage;
        if(total%count == 0 )
            totalPage=total/count;
        else
            totalPage=(total/count + 1);

        if(totalPage == 0)
            totalPage = 1;
        return totalPage;
    }

    public  boolean isHasPreviouse(){
        if(0==start)
            return false;
        return true;
    }

    public boolean isHasNext(){
        if(getLast()==start)
            return false;
        return true;
    }

    public  int getTotal(){
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }
    public void setParam(String param) {
        this.param = param;
    }

    public String toString(){
        return "Page[start = " + start +",count = "+ count +"total = "+ total +"getStart = "+ getStart() +
                "getCount() = "+ getCount() + "isHasPreviouse() = "+isHasPreviouse() + "isHasNext() = "+
                isHasNext() + "getTotalPage() = " + getTotalPage() +"getLast() = "+ getLast() +"]";
    }

}
