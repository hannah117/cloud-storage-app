package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Update;

public interface NoteMapper {

    @Update("UPDATE Notes SET notedescription = #{notedescription}")
            void editNotedDescription();


}
