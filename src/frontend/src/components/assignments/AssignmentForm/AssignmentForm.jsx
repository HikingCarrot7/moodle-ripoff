import { useForm } from 'react-hook-form';

export const AssignmentForm = ({ assignment, onSubmit }) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <label htmlFor="title">Title</label>
      <input
        type="text"
        id="title"
        name="title"
        defaultValue={assignment?.title}
        {...register('title', { required: true })}
      />
      {errors.title && <p>An assignment title is required</p>}

      <label htmlFor="description">Description</label>
      <input
        type="text"
        id="description"
        name="description"
        defaultValue={assignment?.description}
        {...register('description', { required: true })}
      />
      {errors.description && <p>An assignment description is required</p>}

      <label htmlFor="dueDate">Due date</label>
      <input
        type="datetime-local"
        id="dueDate"
        name="dueDate"
        defaultValue={assignment?.dueDate}
        {...register('dueDate', { required: true })}
      />
      {errors.dueDate && <p>A due date is required</p>}

      <label htmlFor="lockAfterDueDate">Lock after due date</label>
      <input
        type="checkbox"
        id="lockAfterDueDate"
        name="lockAfterDueDate"
        defaultChecked={assignment?.lockAfterDueDate}
        {...register('lockAfterDueDate', { required: false })}
      />
      <button type="submit">Submit</button>
    </form>
  );
};
