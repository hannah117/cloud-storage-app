package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FileMapper {

    @Select("SELECT * FROM Files WHERE fileName = #{filename}")
    File getFile(String filename);

    @Insert("INSERT INTO Files (filename, contenttype, filesize, userid, filedata) VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Update("UPDATE Files SET filename = #{filename}")
    void editUsername();

    @Delete("DROP Files WHERE filename = #{filename}")
    File deleteFile(String filename);

    @Select("SELECT * FROM Files")
    List<File> getAllFiles();
}
