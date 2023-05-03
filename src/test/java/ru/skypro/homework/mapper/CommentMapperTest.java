package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Comment;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.skypro.homework.ConstantsTest.*;

class CommentMapperTest {

    @Test
    void shouldMapCommentToCommentDto() {
        CommentDto actual = CommentMapper.INSTANCE.commentToCommentDto(COMMENT_TEST_1);

        assertThat(actual).isNotNull();
        assertThat(actual.getAuthor()).isEqualTo(USER_ID);
        assertThat(actual.getAuthorImage()).isEqualTo(IMAGE);
        assertThat(actual.getAuthorFirstName()).isEqualTo(FIRST_NAME);
        assertThat(actual.getCreatedAt()).isEqualTo(CREATED_AT);
        assertThat(actual.getPk()).isEqualTo(COMMENT_ID_1);
        assertThat(actual.getText()).isEqualTo(TEXT);
    }

    @Test
    void shouldMapCommentDtoToComment() {
        Comment actual = CommentMapper.INSTANCE.commentDtoToComment(COMMENT_DTO_TEST_1);

        assertThat(actual).isNotNull();
        assertThat(actual.getUser().getId()).isEqualTo(USER_ID);
        assertThat(actual.getUser().getImage()).isEqualTo(IMAGE);
        assertThat(actual.getUser().getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(actual.getCreatedAt()).isEqualTo(CREATED_AT);
        assertThat(actual.getId()).isEqualTo(COMMENT_ID_1);
        assertThat(actual.getText()).isEqualTo(TEXT);
    }
}