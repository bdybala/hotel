import React, { Component } from 'react';

import { Panel, Form, Glyphicon } from 'react-bootstrap';

class UserRegisterPanel extends Component {

	render() {
		return (
			<div>
				<Panel>
					<Panel.Heading>
						<Panel.Toggle>
							<Panel.Title className="panelTitle">
								<Glyphicon glyph="glyphicon glyphicon-collapse-down" />
								&nbsp;
								Dodanie nowego pokoju
							</Panel.Title>
						</Panel.Toggle>
					 </Panel.Heading>
				</Panel>
			</div>
		);
	}
}

export default UserRegisterPanel;