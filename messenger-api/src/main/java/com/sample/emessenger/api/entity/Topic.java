package com.sample.emessenger.api.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.persistence.ForeignKey;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Topic")
@Table(name = "topic")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Topic {

	@Id
	@SequenceGenerator(name = "emessenger_db_topic_seq", sequenceName = "emessenger_db_topic_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emessenger_db_topic_seq")
	@Column(name = "id")
	private Long id;

	@Column(name = "name", length = 255)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "topic_subscribers", joinColumns = @JoinColumn(name = "topic_id", foreignKey = @ForeignKey(name = "topic_subscribers_topic_id_fk")), inverseJoinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "topic_subscribers_user_id_fk")))
	private List<User> subscribers = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "topic_messages", uniqueConstraints = {@UniqueConstraint(columnNames = "message_id", name = "topic_messages_message_id_uk")}, joinColumns = @JoinColumn(name = "topic_id", foreignKey = @ForeignKey(name = "topic_messages_topic_id_fk")), inverseJoinColumns = @JoinColumn(name = "message_id", foreignKey = @ForeignKey(name = "topic_messages_message_id_fk")))
	private List<Message> messages = new ArrayList<>();

	@Version
	@Column(name = "OPTLOCK")
	private Long versionId;
}
