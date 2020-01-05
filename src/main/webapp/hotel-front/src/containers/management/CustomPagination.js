import React, { Component } from 'react';

import { Pagination } from 'react-bootstrap';

import './CustomPagination.css';

class CustomPagination extends Component {
	constructor(props) {
		super(props);
		this.handleClickPage = this.handleClickPage.bind(this);

		this.state = {
			totalPages: 1,
			currentPage: 1,
		}
	}

	handleClickPage(page) {
		if (!(page === this.props.currentPage || page === 0 || page > this.props.totalPages )) {
			this.props.changePage(page);
		}
	}

	render() {
		var items = [];

		for (let i = 1; i <= this.props.totalPages; i++) {
			items.push(
				<Pagination.Item key={i} active={i === this.props.currentPage} onClick={(e) => this.handleClickPage(i, e)}>{i}</Pagination.Item>
			);
		}

		return(
			<div className="CustomPagination">
				<Pagination>
					<Pagination.First disabled={this.props.currentPage === 1} onClick={(e) => this.handleClickPage(1, e)} />
					<Pagination.Prev disabled={this.props.currentPage === 1} onClick={(e) => this.handleClickPage(this.props.currentPage-1, e)} />
					{items}
					<Pagination.Next disabled={this.props.currentPage === this.props.totalPages} onClick={(e) => this.handleClickPage(this.props.currentPage+1, e)} />
					<Pagination.Last disabled={this.props.currentPage >= this.props.totalPages} onClick={(e) => this.handleClickPage(this.props.totalPages, e)}/>
				</Pagination>
			</div>
		);
	}
}

export default CustomPagination;
