package ru.clevertec.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ru.clevertec.entity.Certificate;
import ru.clevertec.entity.Tag;
import ru.clevertec.entity.dto.CertificateDtoWithoutTags;
import ru.clevertec.entity.dto.CertificateDtoWithoutTags.CertificateDtoWithoutTagsBuilder;
import ru.clevertec.entity.dto.TagDto;
import ru.clevertec.entity.dto.TagDto.TagDtoBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-10T21:35:24+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 1.8.0_302 (BellSoft)"
)
public class TagMapperImpl implements TagMapper {

    @Override
    public TagDto mapTagToDto(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDtoBuilder tagDto = TagDto.builder();

        tagDto.id( tag.getId() );
        tagDto.name( tag.getName() );
        tagDto.certificates( certificateListToCertificateDtoWithoutTagsList( tag.getCertificates() ) );

        return tagDto.build();
    }

    protected CertificateDtoWithoutTags certificateToCertificateDtoWithoutTags(Certificate certificate) {
        if ( certificate == null ) {
            return null;
        }

        CertificateDtoWithoutTagsBuilder certificateDtoWithoutTags = CertificateDtoWithoutTags.builder();

        certificateDtoWithoutTags.id( certificate.getId() );
        certificateDtoWithoutTags.name( certificate.getName() );
        certificateDtoWithoutTags.description( certificate.getDescription() );
        certificateDtoWithoutTags.price( certificate.getPrice() );
        certificateDtoWithoutTags.duration( certificate.getDuration() );
        certificateDtoWithoutTags.createDate( certificate.getCreateDate() );
        certificateDtoWithoutTags.lastUpdateDate( certificate.getLastUpdateDate() );

        return certificateDtoWithoutTags.build();
    }

    protected List<CertificateDtoWithoutTags> certificateListToCertificateDtoWithoutTagsList(List<Certificate> list) {
        if ( list == null ) {
            return null;
        }

        List<CertificateDtoWithoutTags> list1 = new ArrayList<CertificateDtoWithoutTags>( list.size() );
        for ( Certificate certificate : list ) {
            list1.add( certificateToCertificateDtoWithoutTags( certificate ) );
        }

        return list1;
    }
}
