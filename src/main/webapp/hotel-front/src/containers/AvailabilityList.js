import React, {Component} from 'react';
import {Button, Glyphicon, Table} from "react-bootstrap";
import CustomPagination from "./management/CustomPagination";

class AvailabilityList extends Component {
  constructor(props) {
    super(props);
    this.changePage = this.changePage.bind(this);
    this.handleMakeReservation = this.handleMakeReservation.bind(this);

  }
  handleMakeReservation(id) {
    this.props.handleMakeReservation(id);
  }

  changePage(page) {
    this.props.changePage(page);
  }

  render() {
    const isLoading = this.props.isLoading;

    if (isLoading) {
      return <p>Loading...</p>
    }

    if (this.props.availableRooms) {
      var listOfRooms = this.props.availableRooms.map((item, i) => {
        return <tr key={item.id}>
          <th>{i + 1}</th>
          <th>{item.number}</th>
          <th>{item.maxCapacity}</th>
          <th>{item.roomTypeDescription}</th>
          <th>
            <Button bsStyle="primary"
                    onClick={(e) => this.handleMakeReservation(item.id, e)}>
              <Glyphicon glyph='glyphicon glyphicon-ok'/>
            </Button>
          </th>
        </tr>
      });
    }
    return (
        <div className='AvailabilityList'>
          <CustomPagination changePage={this.changePage}
                            currentPage={this.props.currentPage}
                            totalPages={this.props.totalPages}/>
          <Table responsive striped bordered condensed hover>
            <thead>
            <tr>
              <th>#</th>
              <th>Numer</th>
              <th>Pojemność</th>
              <th>Typ pokoju</th>
              <th>Rezerwacja</th>
            </tr>
            </thead>
            <tbody>
            {listOfRooms}
            </tbody>
          </Table>
        </div>
    )
  }
}

export default AvailabilityList;