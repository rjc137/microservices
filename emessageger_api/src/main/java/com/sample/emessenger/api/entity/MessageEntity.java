package com.sample.emessenger.api.entity;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Message")
@Table(name = "message", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "id" }, name = "emessenger_db_message_email_unique") })
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {

	@Id
	@SequenceGenerator(name = "emessenger_db_message_seq", sequenceName = "emessenger_db_message_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "emessenger_db_message_seq")
	private Long entityId;

	@Email
	@Column(length = 32, insertable = true, name = "id", updatable = false, nullable = false)
	private String id;

	@Column(name = "timestamp")
	private long timeStamp;

	@Email
	@Column(name = "sender", length = 255)
	private String sender;

	@Column(name = "content", length = 65535)
	private String content;

	@ManyToOne(fetch = LAZY, targetEntity = Topic.class, cascade = PERSIST, optional = true)
	private Topic topic;

	@Lob
	@Column(name = "attachments")
	private byte[] attachments;

	@Version
	@Column(name = "OPTLOCK")
	private Long versionId;

}
