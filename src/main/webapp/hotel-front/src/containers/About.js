import React from 'react';

import './About.css';

const About = () => {
  return (
      <div className='About'>
        <h1>O hotelu</h1>
				<div className='rowC'>
					<p>Z wysoko cenionym hotelem Mamaison Hotel Le Regina Warsaw skorzystają Państwo ze znakomitej lokalizacji w historycznym Pałacu Mokronowskich i krótkich dróg na Stary Rynek z warszawskim Zamkiem Królewskim. W naszych pokojach i apartamentach, w restauracji francuskiej wyróżnionej 3 punktami Gault Millau oraz w strefie wellness, Państwa pobyt będzie niezwykle wyjątkowy.</p>
					<p>Mamaison Hotel Le Regina Warsaw stanowi część Mamaison Hotels & Residences. Od 2014 należą one do hoteli CPI Hotels, które witają swoich gości w 28 hotelach w 5 krajach. Podobnie jak wszystkie Mamaison Hotels, również Mamaison Hotel Le Regina Warsaw oferuje pierwszorzędną obsługę. Wszystko to w centrum Warszawy!</p>
				</div>
        <iframe
            src="https://www.google.com/maps/embed?pb=!4v1579638959969!6m8!1m7!1sCAoSLEFGMVFpcE1zMXRWWHNMdHk0OWNzM3ozTkR1S1V4QmpSRkhDQzh2YVdlcWZO!2m2!1d52.253755960095!2d21.007563453173!3f328.36621634152885!4f-27.850110519439838!5f0.7820865974627469"
            width="800" height="600" frameBorder="0"
            allowFullScreen=""/>

      </div>
  );
};

export default About;