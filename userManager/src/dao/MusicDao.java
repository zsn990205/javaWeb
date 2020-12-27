package dao;

import entity.Music;
import util.DBUtils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MusicDao {
    //查询全部歌单
    public List<Music> findMusic(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Music> musicList = new ArrayList<>();
        connection = DBUtils.getConnection();
        String sql = "select * from music";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Music music =  new Music();
                music.setId(rs.getInt("id"));
                music.setTitle(rs.getString("title"));
                music.setSinger(rs.getString("singer"));
                music.setTime(rs.getDate("time"));
                music.setUrl(rs.getString("url"));
                music.setUserid(rs.getInt("userid"));
                musicList.add(music);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getClose(connection,ps,rs);
        }
        return musicList;
    }

    //根据id查找音乐
    public Music findMusicById(int id){
       Connection connection = null;
       Music music = null;
       PreparedStatement ps = null;
       ResultSet rs = null;

        //当sql语句中包含?的时候
        //ps的内容需要赋值
       String sql = "select * from music where id = ?";
       connection = DBUtils.getConnection();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if (rs.next()) {
                music = new Music();
                music.setId(rs.getInt("id"));
                music.setTitle(rs.getString("title"));
                music.setSinger(rs.getString("singer"));
                music.setTime(rs.getDate("time"));
                music.setUrl(rs.getString("url"));
                music.setUserid(rs.getInt("userid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getClose(connection,ps,rs);
        }
        return music;
    }

    //根据关键字查询歌单 模糊查找
    public List<Music> ifMusic(String str){
        //str是我要查找的歌名
      Connection connection = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      List<Music> musics = new ArrayList<>();

      //模糊查找
      String sql = "select * from music where title like '%"+str+"%'";
      connection = DBUtils.getConnection();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Music music = new Music();
                music.setId(rs.getInt("id"));
                music.setTitle(rs.getString("title"));
                music.setSinger(rs.getString("singer"));
                music.setTime(rs.getDate("time"));
                music.setUrl(rs.getString("url"));
                music.setUserid(rs.getInt("userid"));
                musics.add(music);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getClose(connection,ps,rs);
        }
        return musics;
    }

    //上传音乐(将信息插入数据库)
    public int Insert(String title, String singer, String time, String url, int userid) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        connection = DBUtils.getConnection();
        String sql = "insert into music(title,singer,time,url,userid) values (?,?,?,?,?) ";
        try {
            ps =connection.prepareStatement(sql);
            ps.setString(1,title);
            ps.setString(2,singer);
            ps.setString(3,time);
            ps.setString(4,url);
            ps.setInt(5,userid);
            int ret = ps.executeUpdate();
            if (ret == 1) {
            //1表示插入成功 返回值代表影响的行数
            //一个insert代表影响一行
              return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getClose(connection,ps,null);
        }
        return 0;
    }

    //删除指定id的音乐
    public int deleteMusicById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "delete from music where id = ?";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
     //删除时只进行更新就行了
            int ret = ps.executeUpdate();
            if (ret == 1) {
        //ret=1时 表示这首歌在music中已经被删除了 此时我们只需要
        //查找这首歌是否在loveMusic中 如果在也要删除
                if (findLoveMusicOnDel(id)) {
                    int ret2 = deleteLoveMusic(id);
                    if (ret2 == 1) {
                        return 1;
                    }
                }
        //表示这个要删除的歌不在loveMusic中 但是已经被前面删除过了 所以返回1
                    return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getClose(connection,ps,null);
        }
        //在两个地方都没有删除成功
        return 0;
    }

    private int deleteLoveMusic(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "delete from lovemusic where id = ?";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            int ret = ps.executeUpdate();
            if (ret == 1) {
                return ret;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getClose(connection,ps,null);
        }
        //0删除失败 1成功
          return 0;
    }

    private boolean findLoveMusicOnDel(int id) {
      //查找指定的这个歌是不是在喜欢音乐这张表中
      Connection connection = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "select * from lovemusic where music_id = ?";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getClose(connection,ps,rs);
        }
        return false;
    }

    //添加音乐到喜欢的列表中
    public boolean insertLoveMusic(int userId,int musicId) {
      Connection connection = null;
      PreparedStatement ps = null;
      String sql = "insert into lovemusic(user_id,music_id) values(?,?)";
      connection = DBUtils.getConnection();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,userId);
            ps.setInt(2,musicId);
            int ret = ps.executeUpdate();
            if (ret == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getClose(connection,ps,null);
        }
        return false;
    }

    //移除当前用户lovemusic中的歌
    public int removeLoveMusic(int userId,int musicId) {
     Connection connection = null;
     PreparedStatement ps = null;
     String sql = "delete from lovemusic where user_id = ? and music_id = ?";
     connection = DBUtils.getConnection();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,userId);
            ps.setInt(2,musicId);
            int ret = ps.executeUpdate();
            if (ret == 1) {
                return ret;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getClose(connection,ps,null);
        }
        return 0;
    }

    /*
    添加音乐到我喜欢的音乐中
    1.先去我喜欢的音乐中查找是否已经添加过了
    2.如果添加过了 就不必再添加了(不可重复添加)
    3.没添加过 再去添加到我喜欢中
     */
    public boolean findMusicByMusicId(int user_id,int musicID) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from lovemusic where user_id = ? and music_id = ?";
        connection = DBUtils.getConnection();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,user_id);
            ps.setInt(2,musicID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getClose(connection,ps,null);
        }
            return false;
    }

    public List<Music> findLoveMusic(int user_id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Music> musicList = new ArrayList<>();
        String sql = "select music_id,title,singer,time,url,userid from music,lovemusic" +
                " where music.id = lovemusic.music_id and user_id = ?";
        connection = DBUtils.getConnection();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Music music = new Music();
                music.setId(rs.getInt("music_id"));
                music.setTitle(rs.getString("title"));
                music.setSinger(rs.getString("singer"));
                music.setTime(rs.getDate("time"));
                music.setUrl(rs.getString("url"));
                music.setUserid(rs.getInt("userid"));
                musicList.add(music);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getClose(connection,ps,null);
        }
        return musicList;
    }

    //根据关键字进行详细查询
    public static List<Music> ifMusicLove(String str,int user_id){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Music> musicList = new ArrayList<>();
        String sql = "select music_id,title,singer,time,url,userid from music,lovemusic" +
                " where music.id = lovemusic.music_id and user_id = ? and title like '%"+str+"%' ";
        connection = DBUtils.getConnection();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Music music = new Music();
                music.setId(rs.getInt("music_id"));
                music.setTitle(rs.getString("title"));
                music.setSinger(rs.getString("singer"));
                music.setTime(rs.getDate("time"));
                music.setUrl(rs.getString("url"));
                music.setUserid(rs.getInt("userid"));
                musicList.add(music);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getClose(connection,ps,null);
        }
        return musicList;
    }
    public static void main(String[] args) {
        //List<Music> list = findMusic();
        //System.out.println(list);

        //Music music = findMusicById(1);
        //System.out.println(music);

        //List<Music> list = ifMusic("小");
        //System.out.println(list);

         //Insert("声声慢","秦霄贤","2020-07-24","music声声慢",2);

        //System.out.println(deleteMusicById(2));

        //System.out.println(insertLoveMusic(4, 4));

        //System.out.println(removeLoveMusic(4, 4));

        //List<Music> music = ifMusicLove("演",4);
        //System.out.println(music);
    }
}
