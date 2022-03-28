package uz.pdp.appcinemarestservice.entity.attachements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appcinemarestservice.entity.Actor;
import uz.pdp.appcinemarestservice.entity.template.AbsEntity;

import javax.persistence.*;

// Nurkulov Nodirbek 3/15/2022  7:11 AM
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "attachments")
public class Attachment extends AbsEntity {
    private String originalFileName;

    private long size;

    private String contentType;

    private String name;

    @OneToOne
    @JoinTable(name = "attachments_actor",
            joinColumns = @JoinColumn(name = "attachment_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Actor actor;

    @OneToOne(mappedBy = "attachment",cascade = CascadeType.ALL)
    @JoinTable(name = "attachments_attachment_content",
            joinColumns = @JoinColumn(name = "attachment_id"),
            inverseJoinColumns = @JoinColumn(name = "attachment_content_id"))
    private AttachmentContent attachmentContent;

    public AttachmentContent getAttachmentContent() {
        return attachmentContent;
    }

    public void setAttachmentContent(AttachmentContent attachmentContent) {
        this.attachmentContent = attachmentContent;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
