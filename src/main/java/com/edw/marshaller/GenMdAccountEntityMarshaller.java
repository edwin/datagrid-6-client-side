package com.edw.marshaller;

import com.edw.model.GenMdAccountEntity;
import org.infinispan.protostream.MessageMarshaller;

import java.io.IOException;
import java.util.Random;

/**
 * <pre>
 *  com.edw.marshaller.GenMdAccountEntityMarshaller
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 28 May 2025 15:34
 */

public class GenMdAccountEntityMarshaller implements MessageMarshaller<GenMdAccountEntity> {
    @Override
    public GenMdAccountEntity readFrom(ProtoStreamReader protoStreamReader) throws IOException {
        return new GenMdAccountEntity(protoStreamReader.readLong("ACCOUNT_ID"), protoStreamReader.readString("ACCOUNT_CODE"), protoStreamReader.readLong("MEMBER_ID"), protoStreamReader.readString("CLIENT_CODE"),
                protoStreamReader.readString("ACCOUNT_TYPE"), protoStreamReader.readString("CHECK_DIGIT"), protoStreamReader.readString("ACCOUNT_NAME"), protoStreamReader.readString("ACCOUNT_POSITION"),
                protoStreamReader.readString("CORRESPOND_ACNO"), protoStreamReader.readString("BLOCKING_REASON"), protoStreamReader.readString("ACCOUNT_STATUS"), protoStreamReader.readString("CREATED_BY"),
                protoStreamReader.readDate("CREATED_ON"), protoStreamReader.readString("MODIFIED_BY"), protoStreamReader.readDate("MODIFIED_ON"), protoStreamReader.readString("CHECKED_BY"),
                protoStreamReader.readDate("checkedOn"), protoStreamReader.readString("approvedBy"), protoStreamReader.readDate("approvedOn"), protoStreamReader.readString("accountBuyerSeller")
        );
    }

    @Override
    public void writeTo(ProtoStreamWriter protoStreamWriter, GenMdAccountEntity genMdAccountEntity) throws IOException {
        protoStreamWriter.writeLong("ACCOUNT_ID", genMdAccountEntity.getAccountId());
        protoStreamWriter.writeString("ACCOUNT_CODE", genMdAccountEntity.getAccountCode());
        protoStreamWriter.writeLong("MEMBER_ID", genMdAccountEntity.getMemberId());
        protoStreamWriter.writeString("CLIENT_CODE", genMdAccountEntity.getClientCode());
        protoStreamWriter.writeString("ACCOUNT_TYPE", genMdAccountEntity.getAccountType());
        protoStreamWriter.writeString("CHECK_DIGIT", genMdAccountEntity.getCheckDigit());
        protoStreamWriter.writeString("ACCOUNT_NAME", genMdAccountEntity.getAccountName());
        protoStreamWriter.writeString("ACCOUNT_POSITION", genMdAccountEntity.getAccountPosition());
        protoStreamWriter.writeString("CORRESPOND_ACNO", genMdAccountEntity.getCorrespondAcNo());
        protoStreamWriter.writeString("BLOCKING_REASON", genMdAccountEntity.getBlockingReason());
        protoStreamWriter.writeString("ACCOUNT_STATUS", genMdAccountEntity.getAccountStatus());
        protoStreamWriter.writeString("CREATED_BY", genMdAccountEntity.getCreatedBy());
        protoStreamWriter.writeDate("CREATED_ON", genMdAccountEntity.getCreatedOn());
        protoStreamWriter.writeString("MODIFIED_BY", genMdAccountEntity.getModifiedBy());
        protoStreamWriter.writeDate("MODIFIED_ON", genMdAccountEntity.getModifiedOn());
        protoStreamWriter.writeString("CHECKED_BY", genMdAccountEntity.getCheckedBy());
        protoStreamWriter.writeDate("checkedOn", genMdAccountEntity.getCheckedOn());
        protoStreamWriter.writeString("approvedBy", genMdAccountEntity.getApprovedBy());
        protoStreamWriter.writeDate("approvedOn", genMdAccountEntity.getApprovedOn());
        protoStreamWriter.writeString("accountBuyerSeller", genMdAccountEntity.getAccountBuyerSeller());
    }

    @Override
    public Class<? extends GenMdAccountEntity> getJavaClass() {
        return GenMdAccountEntity.class;
    }

    @Override
    public String getTypeName() {
        return "default.GenMdAccountEntity";
    }
}
