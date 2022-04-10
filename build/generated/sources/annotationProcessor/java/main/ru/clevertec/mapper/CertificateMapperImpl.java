package ru.clevertec.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ru.clevertec.entity.Certificate;
import ru.clevertec.entity.Tag;
import ru.clevertec.entity.dto.CertificateDto;
import ru.clevertec.entity.dto.CertificateDto.CertificateDtoBuilder;
import ru.clevertec.entity.dto.TagDtoWithoutCertificates;
import ru.clevertec.entity.dto.TagDtoWithoutCertificates.TagDtoWithoutCertificatesBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-10T21:35:24+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 1.8.0_302 (BellSoft)"
)
public class CertificateMapperImpl implements CertificateMapper {

    @Override
    public CertificateDto mapToCertificateDto(Certificate certificate) {
        if ( certificate == null ) {
            return null;
        }

        CertificateDtoBuilder certificateDto = CertificateDto.builder();

        certificateDto.id( certificate.getId() );
        certificateDto.name( certificate.getName() );
        certificateDto.description( certificate.getDescription() );
        certificateDto.price( certificate.getPrice() );
        certificateDto.duration( certificate.getDuration() );
        certificateDto.createDate( certificate.getCreateDate() );
        certificateDto.lastUpdateDate( certificate.getLastUpdateDate() );
        certificateDto.tags( tagListToTagDtoWithoutCertificatesList( certificate.getTags() ) );

        return certificateDto.build();
    }

    protected TagDtoWithoutCertificates tagToTagDtoWithoutCertificates(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDtoWithoutCertificatesBuilder tagDtoWithoutCertificates = TagDtoWithoutCertificates.builder();

        tagDtoWithoutCertificates.id( tag.getId() );
        tagDtoWithoutCertificates.name( tag.getName() );

        return tagDtoWithoutCertificates.build();
    }

    protected List<TagDtoWithoutCertificates> tagListToTagDtoWithoutCertificatesList(List<Tag> list) {
        if ( list == null ) {
            return null;
        }

        List<TagDtoWithoutCertificates> list1 = new ArrayList<TagDtoWithoutCertificates>( list.size() );
        for ( Tag tag : list ) {
            list1.add( tagToTagDtoWithoutCertificates( tag ) );
        }

        return list1;
    }
}
