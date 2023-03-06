import { Button } from 'primereact/button';
import { Card } from 'primereact/card';
import { useRouter } from 'next/router';
import { Badge } from 'primereact/badge';

export const CourseCard = ({ course }) => {
  const router = useRouter();
  const { name, description } = course;

  const goToCourse = () => {
    router.push(`/courses/${course.id}`);
  };

  return (
    <div className="card flex justify-content-center">
      <Card
        title={name}
        header={<Header />}
        footer={<Footer onGoToCourse={goToCourse} />}
        className="md:w-25rem">
        <p className="m-0" style={{ minHeight: '120px' }}>
          {description}
        </p>
        <Badge value={`Enrollments: ${course.enrollmentCount}`} />
      </Card>
    </div>
  );
};

const Header = () => {
  return (
    <img
      alt="Card"
      src="https://primefaces.org/cdn/primereact/images/usercard.png"
    />
  );
};

const Footer = ({ onGoToCourse }) => {
  return (
    <div className="flex flex-wrap justify-content-end gap-2">
      <Button
        label="Go to course"
        icon="pi pi-arrow-right"
        onClick={onGoToCourse}
      />
    </div>
  );
};
