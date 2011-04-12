package com.gitblit.wicket.pages;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;

import com.gitblit.utils.JGitUtils;
import com.gitblit.wicket.LinkPanel;
import com.gitblit.wicket.RepositoryPage;
import com.gitblit.wicket.WicketUtils;


public class TagPage extends RepositoryPage {

	public TagPage(PageParameters params) {
		super(params);

		Repository r = getRepository();
		RevCommit c = JGitUtils.getCommit(r, objectId);

		add(new LinkPanel("commit", "title", c.getName(), CommitPage.class, newCommitParameter()));

		add(new LinkPanel("tagId", "list", c.getName(), CommitPage.class, newCommitParameter(c.getName())));
		add(new Label("tagAuthor", JGitUtils.getDisplayName(c.getAuthorIdent())));
		add(WicketUtils.createTimestampLabel("tagDate", c.getAuthorIdent().getWhen(), getTimeZone()));

		addFullText("fullMessage", c.getFullMessage(), true);
	}
	
	@Override
	protected String getPageName() {
		return getString("gb.tag");
	}
}
